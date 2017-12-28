
public class HashTable {

	/*Functions Needed
	 * 
	 * void insert(String key, int value)
	 * int find(String key)
	 * void delete(String key)
	 */
	ListNode[] arr;
	HashTable(){
		arr = new ListNode[13];
	}

	public void insert(String key,int value){
		long location = ELFhash(key,arr.length);
		ListNode creado = arr[ (int)location ];
		creado = new ListNode(key,value,creado);
		arr[(int) location] = creado;
	}
	// Did i build this correctly? return location of correct key if found otherwise return -1
	public Integer find(String key){
		long loc = ELFhash(key,arr.length);
		ListNode temp;
		for(temp = arr[(int)loc]; temp != null; temp = temp.next){
			if(temp.key.equals(key)){
				//System.out.println("Foud at:" + (int)loc);
				return (Integer)temp.data;
			}
		}
		return null;
	}
	
	public int findInHash(String key){
		long loc = ELFhash(key,arr.length);
		ListNode temp;
		for(temp = arr[(int)loc]; temp != null; temp = temp.next){
			if(temp.key.equals(key)){
				//System.out.println("Foud at:" + (int)loc);
				return (int)loc;
			}
		}
		return -1;
	}

	public void delete(String key){
		int loc = findInHash(key);
		if ( loc == -1){
			//System.out.println("Value to be deleted not in list");
		}else{
			ListNode head = arr[loc];
			ListNode followingNode= null;
			ListNode prev = null;
			ListNode tmp = head;
			//Special Case: Element is at head
			if(head.key.equals(key)){
				//System.out.println("before head is " + head.key);
				arr[loc]=head.next;
				//System.out.println("after head is " + head.key);
			}else{
			//Next case : Value No in head
				while (tmp != null){
					if(tmp.key.equals(key) ){
						break;
					}
					//System.out.println("in here");
					prev=tmp;
					tmp = tmp.next;
					followingNode = tmp.next;
					
				}
				//System.out.println("prev:" + prev.key);
				//System.out.println("tmp:"+ tmp.key);
				//System.out.println("following:"+ followingNode.key);
				
				prev.next = followingNode;
				
			}
			
		}
		for( ListNode m = arr[loc]; m!=null; m=m.next){
			//System.out.println(m.key);
		}


	}



static long ELFhash(String key, int tablesize) {
	long h = 0;
	long g;
	int i;
	for (i=0; i<key.length(); i++) {
		h = (h << 4) + (int) key.charAt(i);
		g = h & 0xF0000000L;
		if (g != 0)
			h ^= g >>> 24;
		h &= ~g;
	}
	return h % tablesize;
}

public void print(){
	for(int i = 0; i<arr.length; i++){
		System.out.println("index" + i);
		for(ListNode temp = arr[i]; temp!=null; temp = temp.next){
			String node=  "	KEY:"+temp.key+"\n"
					+ "	VAL:" + temp.data+"\n\n";
			System.out.print(node);
		}
		System.out.println();
	}
}

}
