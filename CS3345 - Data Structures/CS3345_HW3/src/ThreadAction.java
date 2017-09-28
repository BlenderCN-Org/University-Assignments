import java.util.ArrayList;

public class ThreadAction implements Runnable {
	
	String threadName;
	int threadType;
	
	HashMap mHashMap;
	String key;
	String type;
	String def;
	
	Thread t;
	
	public ThreadAction (String threadName, int threadType, HashMap mHashMap, 
						 String key, String type, String def) {
		this.threadName = threadName;
		this.threadType = threadType;
		this.mHashMap = mHashMap;
		this.key = key;
		this.type = type;
		this.def = def;
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
		//System.out.println("Ending Thread: " + threadName);
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
