import java.util.ArrayList;
import java.util.Arrays;

public class HashMap {
	
	ArrayList<String> KeySet;
	
	public static HashNode[] LinearProbing;
	public int LinearSize;
	public int LinearInvestigation;
	public long LinearTime;
	
	public static HashNode[] QuadraticProbing;
	public int QuadraticSize;
	public int QuadraticInvestigation;
	public long QuadraticTime;
	
	public static HashNode[][] SeperateChaining;
	public int ChainingSize;
	public int ChainingInvestigation;
	public long ChainingTime;
	
	public static HashNode[] DoubleHashing;
	public int DoubleSize;
	public int DoubleInvestigation;
	public long DoubleTime;
	
	private static ArrayList<Integer> prime_list;
	private int prime_hashval;
	private int primePos;
	
	public HashMap() {
		LinearProbing = new HashNode[2];
		LinearSize = 0;
		LinearInvestigation = 0;
		LinearTime = 0;
		
		QuadraticProbing = new HashNode[2];
		QuadraticSize = 0;
		QuadraticInvestigation = 0;
		QuadraticTime = 0;
		
		SeperateChaining = new HashNode[1][2];
		ChainingSize = 0;
		ChainingInvestigation = 0;
		ChainingTime = 0;
		
		DoubleHashing = new HashNode[2];
		DoubleSize = 0;
		DoubleInvestigation = 0;
		DoubleTime = 0;
		
		prime_list = getPrime(155286*2);
		primePos = 0;
		prime_hashval = prime_list.get(primePos);
		
		KeySet = new ArrayList<String>();
	}
	
	public void insertLinearProbing(String key, String type, String def) {
		long startTime = System.nanoTime();
		
		int hash = 1; // Starting HASH at a prime number, to produce more unique results
		int linear_probe = 0;
		
		// Initial Hash
		for(int i = 0; i < key.length(); i++) {
			hash = 3 * hash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}
		
		hash = Math.abs((hash + linear_probe) % LinearProbing.length);
		
		while(LinearProbing[hash] != null) {					
			hash = Math.abs((hash + linear_probe++) % LinearProbing.length);		
			LinearInvestigation++;
		}
		LinearProbing[hash] = new HashNode(key, type, def);
		KeySet.add(key);
		LinearSize++;
		
		if(LinearSize >= LinearProbing.length/2) {
			HashNode[] newTable = new HashNode[LinearProbing.length*2];
			for(int i = 0; i < LinearProbing.length; i++) {
				newTable[i] = LinearProbing[i];
			}
			LinearProbing = newTable;
		}
		
		long endTime = System.nanoTime();
		LinearTime += (endTime - startTime)/1000000;
	}

	public void insertQuadraticProbing(String key, String type, String def) {
		long startTime = System.nanoTime();
		
		int hash = 1; // Starting HASH at a prime number, to produce more unique results
		int quadratic_probe = 0;
		
		// Initial Hash
		for(int i = 0; i < key.length(); i++) {
			hash = 1 * hash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}
		
		hash = Math.abs((hash + (int)Math.pow(quadratic_probe, 2)) % QuadraticProbing.length);
		
		
		while(QuadraticProbing[hash] != null) {					
			hash = Math.abs((hash + quadratic_probe*quadratic_probe++) % QuadraticProbing.length);
			QuadraticInvestigation++;
		}
		
		QuadraticProbing[hash] = new HashNode(key, type, def);
		KeySet.add(key);
		QuadraticSize++;
		
		try {
			if(QuadraticSize >= QuadraticProbing.length/2) {
				HashNode[] newTable = new HashNode[QuadraticProbing.length*2];
				for(int i = 0; i < QuadraticProbing.length; i++) {
					newTable[i] = QuadraticProbing[i];
				}
				QuadraticProbing = newTable;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		QuadraticTime += (endTime - startTime)/1000000;
	}
	
	public void insertSeperateChaining(String key, String type, String def) {
		long startTime = System.nanoTime();
		
		int hash = 1; // Starting HASH at a prime number, to produce more unique results				    
		
		// Initial Hash
		for(int i = 0; i < key.length(); i++) {
			hash = 3 * hash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}		
		hash = Math.abs(hash % SeperateChaining.length);
		
		ChainingInvestigation++;
		KeySet.add(key);
		
		if(SeperateChaining[hash] == null) {
			ChainingSize++;
			SeperateChaining[hash] = new HashNode[2];
			SeperateChaining[hash][0] = new HashNode(key, type, def); 
		} else {
			SeperateChaining[hash][getFreeSpace(SeperateChaining[hash])] = new HashNode(key, type, def);
		}
		
		if(getNumElements(SeperateChaining[hash]) >= SeperateChaining[hash].length/2) {
			HashNode[] newTable = new HashNode[SeperateChaining[hash].length*2];
			for(int i = 0; i < SeperateChaining[hash].length; i++) {
				newTable[i] = SeperateChaining[hash][i];
			}
			SeperateChaining[hash] = newTable;
		}
		
		if(ChainingSize >= SeperateChaining.length/2) {
			HashNode[][] tmp = new HashNode[SeperateChaining.length*2][];
			for(int i = 0; i < SeperateChaining.length; i++) {
				
				int len = 0;
				if(SeperateChaining[i] != null) { len = SeperateChaining[i].length;	} 
				else { len = 0;	}
				
				for(int j = 0; j < len; j++) {
					tmp[i] = SeperateChaining[i];					
				}
			}
			SeperateChaining = tmp;
		}
		
		long endTime = System.nanoTime();
		ChainingTime += (endTime - startTime)/1000000;
	}
	
	public void insertDoubleHashing(String key, String type, String def) {
		
		long startTime = System.nanoTime();
		
		int primaryHash = 0;
		
		for(int i = 0; i < key.length(); i++) {
			primaryHash = 3 * primaryHash + key.charAt(i);
		}
		
		if(prime_list.get(primePos+1) < DoubleSize) {
			prime_hashval = prime_list.get(primePos+1);
			primePos++;
		}
		
		int hash1 = Math.abs(primaryHash % DoubleHashing.length);
		int hash2 = Math.abs(prime_hashval - primaryHash % prime_hashval);
		
		while(DoubleHashing[hash1] != null) {
			hash1 += hash2;
			hash1 %= DoubleHashing.length;
			DoubleInvestigation++;
		}
		
		DoubleHashing[hash1] = new HashNode(key, type, def);
		KeySet.add(key);
		DoubleSize++;

		if(DoubleSize >= DoubleHashing.length/2) {
			HashNode[] newTable = new HashNode[DoubleHashing.length*2];
			for(int i = 0; i < DoubleHashing.length; i++) {
				newTable[i] = DoubleHashing[i];
			}
			DoubleHashing = newTable;
		}
		
		long endTime = System.nanoTime();
		DoubleTime += (endTime - startTime)/1000000;
	}
	
	private ArrayList<Integer> getPrime(int prime_limit) {
		// Use Sieve of Eratosthenes for primes : *Thanks Project Euler!*		
		Boolean[] sieve = new Boolean[prime_limit];
		Arrays.fill(sieve, true);
		
		for(int i = 2; i < (int)Math.sqrt(prime_limit); i++) {
			if(sieve[i]) {
				for(int j = (int)Math.pow(i,2); j < prime_limit; j += i) {
					sieve[j] = false;
				}
			}
		}
				
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = sieve.length-1; i > 0; i--) {
			if(sieve[i]) {
				list.add(0,i);
			}
		}
		
		return list;
	}

	public ArrayList<String> getKeySet(){
		return KeySet;
	}
	
	private int getFreeSpace(HashNode[] array) {
		for(int i = 0; i < array.length; i++) {
			ChainingInvestigation++;
			if(array[i] == null) {
				return i;
			}
		}		
		return -1; // Should never get to this point
	}
	
	private int getNumElements(HashNode[] array) {		
		int numElements = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				numElements++;
			}
		}
		return numElements;
	}

	public String find(String key, int type) {
		
		int hash = 1;
		
		// Linear Probing
		switch(type) {
			case 1:
				hash = 1;
				int linear_probe = 0;				
				
				for(int i = 0; i < key.length(); i++) {
					hash = 3 * hash + key.charAt(i);
				}				
				hash = Math.abs((hash + linear_probe) % LinearProbing.length);
				
				while(true) {
					if(LinearProbing[hash] != null) {
						if(LinearProbing[hash].name .equals(key)) {
							return "(" + LinearProbing[hash].type + ") " +  LinearProbing[hash].def;
						}
					}
					hash = Math.abs((hash + linear_probe++) % LinearProbing.length);		
				}				
				
		// Quadratic Probing
			case 2:
				hash = 1;
				int quadratic_probe = 0;				
				
				for(int i = 0; i < key.length(); i++) {
					hash = 1 * hash + key.charAt(i);
				}				
				hash = Math.abs((hash + (int)Math.pow(quadratic_probe, 2)) % QuadraticProbing.length);				
				
				while(true) {
					if(QuadraticProbing[hash] != null) {
						if(QuadraticProbing[hash].name .equals(key)) {
							return "(" + QuadraticProbing[hash].type + ") " +  QuadraticProbing[hash].def;
						}
					}
					hash = Math.abs((hash + quadratic_probe*quadratic_probe++) % QuadraticProbing.length);
				}				
				
			// Seperate  Chaining
			case 3:
				hash = 1;    
				
				for(int i = 0; i < key.length(); i++) {
					hash = 3 * hash + key.charAt(i);
				}		
				hash = Math.abs(hash % SeperateChaining.length);
				
				for(int i = 0; i < SeperateChaining.length; i++) {
					if(SeperateChaining[i] != null) {
						for(int j = 0; j < SeperateChaining[i].length; j++) {
							if(SeperateChaining[i][j] != null) {
								if(SeperateChaining[i][j].name.equals(key)) {
									return "(" + SeperateChaining[i][j].type + ") " +  SeperateChaining[i][j].def; 
								}
							}	
						}
					}
				}
				break;
				
			// Double Hashing
			case 4:
				int primaryHash = 0;
				
				for(int i = 0; i < key.length(); i++) {
					primaryHash = 3 * primaryHash + key.charAt(i);
				}				
				
				int hash1 = Math.abs(primaryHash % DoubleHashing.length);
				int hash2 = Math.abs(prime_hashval - primaryHash % prime_hashval);
				

				while(true) {
					if(DoubleHashing[hash1] != null) {
						if(DoubleHashing[hash1].name .equals(key)) {
							return "(" + DoubleHashing[hash1].type + ") " +  DoubleHashing[hash1].def;
						}
					}
					hash1 += hash2;
					hash1 %= DoubleHashing.length;
				}	
		}		
		return "";			
	}
	
	public HashNode[] getLinearProbing() {
		return LinearProbing;
	}
	
	public HashNode[] getQuadraticProbing() {
		return QuadraticProbing;
	}
	
	public HashNode[][] getSeperateChaining() {
		return SeperateChaining;
	}
	
	public HashNode[] getDoubleHashing() {
		return DoubleHashing;
	}
}
