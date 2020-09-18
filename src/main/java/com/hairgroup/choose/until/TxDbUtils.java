package com.hairgroup.choose.until;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 带有事物功能的连接池工具 有点AOP 有点动态代理
 * 
 * @author 13597
 *
 */
public class TxDbUtils {

	private TxDbUtils() {
	}

	// 获取到连接池对象（也就是数据源对象）
	private static DataSource source = new ComboPooledDataSource();

	// 是否开启事物的标记
	private static ThreadLocal<Boolean> isTx_local = new ThreadLocal<Boolean>() {

		@Override
		protected Boolean initialValue() {
			//设置为false说明没有开启事物
			return false;
		};

	};
	
	//保存真实的链接代理链接，改造close方法
	private static ThreadLocal<Connection> proxyConn_local  =new ThreadLocal<Connection>() {};
	
	//保存真实的链接
	private static ThreadLocal<Connection> realConn_local = new ThreadLocal<Connection>() {};
	
	/**
	 * 开启事物的方法
	 * @throws SQLException 
	 */
	public static void startTx() throws SQLException {
		// 这是标记为true
		isTx_local.set(true);
		
		//首先获取链接对象，然后对该连接对象进行处理
		final Connection connection = source.getConnection();
		
		//开启事物，也就是不自动提交事物
		connection.setAutoCommit(false);
		
		//将该连接对象保存到线程中
		realConn_local.set(connection);
		
		// 由于一个事物需要执行多条SQL语句，每个SQL执行完毕之后都要关闭连接
		// 这样的话，后面的SQL就没办法执行了，所以我们需要改造close方法，使它不能关闭连接
		Connection proyxConn = (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces()
				, new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("close".equals(method.getName())) {
							return null;
						} else {
							return method.invoke(connection, args);
						}
					}
				});
		
		proxyConn_local.set(proyxConn);
		
	}
	
	/**
	 * 提交事务
	 */
	public static void commit() {
		//关闭的是代理的connection对象
		DbUtils.commitAndCloseQuietly(proxyConn_local.get());
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollBack() {
		//回滚的是代理的connection对象
		DbUtils.rollbackAndCloseQuietly(proxyConn_local.get());
	}
	
	/**
	 * 获取到连接池的对象
	 * 如果没有开启过事务，则返回的就是普通的连接池对象
	 * 
	 * 如果开启过事务，则必须返回一个改造过的getConnection对象
	 * 
	 * @return
	 */
	public static DataSource getSource() {
		if (isTx_local.get()) {
			// 这里也就是说，开启了事务
			// 所以我们需要改造getConnect()方法
			return (DataSource) Proxy.newProxyInstance(source.getClass().getClassLoader(),
					new Class[] {DataSource.class}, new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if ("getConnection".equals(method.getName())) {
								return proxyConn_local.get();
							} else {
								return method.invoke(source, args);
							}
						}
					});
		} else {
			// 这里也就是说，没有开启事务
			// 直接返回普通的连接对象
			return source;
		}
	}
	
	/**
	 * 释放资源
	 */
	public static void release() {
		
		// 关闭真实对象的链接
		DbUtils.closeQuietly(realConn_local.get());
		
		// 把对象和ThreadLocal拆开
		realConn_local.remove();
		proxyConn_local.remove();
		isTx_local.remove();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
