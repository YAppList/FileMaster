package com.rupeng.view.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;


import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.rupeng.view.constants.Constants;
import com.rupeng.view.utility.Utility;

public class WebServer extends Thread {
	private static final String SERVER_NAME = "AndWebServer";
	private static final String ALL_PATTERN = "*";
	private static final String MESSAGE_PATTERN = "/message*";
	private static final String FOLDER_PATTERN = "/dir*";

	private volatile  boolean isRunning = false;
	private Context context = null;
	private int serverPort = 0;

	private BasicHttpProcessor httpproc = null;
	private BasicHttpContext httpContext = null;
	private HttpService httpService = null;
	private HttpRequestHandlerRegistry registry = null;
	private NotificationManager notifyManager = null;

	public WebServer(Context context, NotificationManager notifyManager) {
		super(SERVER_NAME);

		this.setContext(context);
		this.setNotifyManager(notifyManager);

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);

		serverPort = Integer
				.parseInt(pref.getString(Constants.PREF_SERVER_PORT, ""
						+ Constants.DEFAULT_SERVER_PORT));
	//	serverPort = 8099;
		httpproc = new BasicHttpProcessor();
		httpContext = new BasicHttpContext();

		httpproc.addInterceptor(new ResponseDate());
		httpproc.addInterceptor(new ResponseServer());
		httpproc.addInterceptor(new ResponseContent());
		httpproc.addInterceptor(new ResponseConnControl());

		httpService = new HttpService(httpproc,
				new DefaultConnectionReuseStrategy(),
				new DefaultHttpResponseFactory());

		registry = new HttpRequestHandlerRegistry();
		registry.register(ALL_PATTERN, new HomePageHandler(context));
		registry.register(MESSAGE_PATTERN, new MessageCommandHandler(context,
				notifyManager));
		registry.register(FOLDER_PATTERN, new FolderCommandHandler(context,
				serverPort));
		httpService.setHandlerResolver(registry);
	}

	Map<String, String> getParams(String string) {
		Map<String, String> params = new HashMap<String, String>();
		String[] strs = string.split("&");
		String[] pars;
		for (String str : strs) {
			pars = str.split("=");
			params.put(pars[0], pars.length > 1 ? pars[1] : "");
		}
		return params;
	}

	@Override
	public void run() {
		super.run();

		try {
			Log.i("yzy","serverPort="+serverPort +" ip="+ Utility.getIp(context));

			ServerSocket serverSocket = new ServerSocket(serverPort);

			serverSocket.setReuseAddress(true);
			while (isRunning) {
				try {
					final Socket socket = serverSocket.accept();

					DefaultHttpServerConnection serverConnection = new DefaultHttpServerConnection();

					serverConnection.bind(socket, new BasicHttpParams());

					httpService.handleRequest(serverConnection, httpContext);

					serverConnection.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (HttpException e) {
					e.printStackTrace();
				}
			}

			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void startThread() {
		isRunning = true;

		super.start();
	}

	public synchronized void stopThread() {
		isRunning = false;
	}

	public void setNotifyManager(NotificationManager notifyManager) {
		this.notifyManager = notifyManager;
	}

	public NotificationManager getNotifyManager() {
		return notifyManager;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}
}
