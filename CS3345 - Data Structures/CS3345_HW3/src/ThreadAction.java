import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadAction implements Runnable {
	
	String threadName;
	int threadType;
	
	HashMap mHashMap;
	String key;
	String type;
	String def;
	
	Thread t;
	FileWriter fw;
	
	public ThreadAction (String threadName, int threadType, HashMap mHashMap, 
						 String key, String type, String def, FileWriter fw) {
		this.threadName = threadName;
		this.threadType = threadType;
		this.mHashMap = mHashMap;
		this.key = key;
		this.type = type;
		this.def = def;
		this.fw = fw;
	}

	@Override
	public void run() {
		//System.out.println("Started Thread: " + threadName);
		try {
			switch(threadType) {
			case 1:
				mHashMap.insertLinearProbing(key, type, def);
				break;
			case 2:
				mHashMap.insertQuadraticProbing(key, type, def);
				break;
			case 3:
				mHashMap.insertSeperateChaining(key, type, def);
				break;
			case 4:
				mHashMap.insertDoubleHashing(key, type, def);
				break;
			default:
				System.out.println("Switch Thread Failed");
				break;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fw.write("Ending Thread: " + threadName + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start () {
	    if (t == null) {
	       t = new Thread (this, threadName);
	       t.start();
	    }
	}
	
	public String toString() {
		return threadName;
	}

}
