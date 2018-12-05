package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeviceTest {

	int numThreads = 1;
	Thread[] threads = new Thread[numThreads];
	List<DeviceRequest> deviceRequests = new ArrayList();
	
	private void createDeviceRequestObjects() {
		File folder = new File("../deviceRequests");
		File[] listOfFiles = folder.listFiles();
		for(File file: listOfFiles) {
			if(file.isFile()) {
				DeviceRequest deviceRequest = new DeviceRequest(file.getPath());
				deviceRequests.add(deviceRequest);
			}
		}
	}
	
	public void start() {
		createDeviceRequestObjects();
		for(int i=0; i < numThreads; i++) {
		    DeviceRequestThread deReqThread = new DeviceRequestThread(deviceRequests.get(i));
		     threads[i] = new Thread(deReqThread);
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	public void stop() throws InterruptedException {
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}
