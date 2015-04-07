package eda095.lab3.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
	public static Socket s;

	public static void main(String[] args) {
		try {
			Main.s = new Socket("localhost",1338);
		
			ExecutorService es = Executors.newFixedThreadPool(2);

			es.submit(new Runnable() {

				@Override
				public void run() {
					try {
						InputStream is = Main.s.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						String line;
						while ((line = br.readLine()) != null) {
							System.out.println(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			es.submit(new Runnable() {

				@Override
				public void run() {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String line;
					OutputStream os;
					try {
						os = Main.s.getOutputStream();
						while ((line = br.readLine()) != null) {
							os.write((line+'\n').getBytes());
							os.flush();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} catch ( IOException e) {
			System.out.println("you no call here½!");
			e.printStackTrace();
		}

	}

}
