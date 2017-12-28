
public class BinomialQueue {
	BinomialNode root;
	BinomialNode[] queue;
	int size;

	BinomialQueue(int size){
		root = null;
		queue = new BinomialNode[size];
		size = 0 ;
		for(int i = 0; i < queue.length; i++){
			queue[i]=null;
		}
	}
	// Last First 
	public  void insert(int elem, int priority){
		//		System.out.println("Insert is called ");
		BinomialNode meh = new BinomialNode(null,null,null,priority,elem);
		queue[meh.value] = meh;
		size++;
		root = merge(root,meh);
		//		printQueue(root);
		//		System.out.println();
		//		System.out.println();

		root = combine(root);
		//		System.out.println("After Combining ");
		//    	printQueue(root);
		//		System.out.println("----------------");
		//		System.out.println();
	}


	//the Merge Function

	public static BinomialNode merge(BinomialNode heap1, BinomialNode heap2){
		// first two cases check to see if either heap is null and then returns that same non-null heap
		//System.out.println("Merge is called");
		if(heap1 == null){
			return heap2;
		}else if(heap2 == null){
			return heap1;
			//the next code will account for both of them not being null and will merge accordin to degree 
		}else if(heap1.degree <= heap2.degree){
			heap1.rightSib=merge(heap1.rightSib,heap2);
			return heap1;
		}else{
			heap2.rightSib=merge(heap2.rightSib,heap1);
			return heap2;
		}
	}

	//  Now the Combine
	public BinomialNode combine (BinomialNode m){
		if(m != null){
			if(m.rightSib == null){
				return m;
			} else if (m.degree != m.rightSib.degree){
				BinomialNode rest = m.rightSib;
				m.rightSib = combine(rest);
				return m;
			}else{
				BinomialNode sib = m.rightSib;			
				BinomialNode thirdSib = sib.rightSib;
				BinomialNode rest;
				// Combine 2 case

				// Compare priorites --
				///   min pointer point to smaller of m and sib
				///   max pointer larger of m and sib

				//// make larger child of smaller (3 pointer changes, one degree change)

				/// attach min back into thirdSib (may be null, but that's OK)
				/// call combine on min (now attched to thirdsib)
				///  return that
				if(m.degree == sib.degree){
					if ( (thirdSib != null) && (thirdSib.degree == sib.degree) ){
						//System.out.println(rest);
						rest = thirdSib.rightSib;
						//						System.out.println(rest);
						m.rightSib = combine(sib);
						//						System.out.println(m.rightSib.rightSib);
						//m.rightSib.rightSib =rest;
						return m;
					}
					else{
						BinomialNode min;
						BinomialNode max;

						if(m.priority <= sib.priority ){
							max= sib;
							min = m;
						}
						else{
							max = m;
							min = sib;
						}
						rest = max.rightSib;
						max.parent = min;
						max.rightSib =min.leftChild;
						min.leftChild = max;
						min.rightSib=thirdSib;
						min.degree +=1;
						return combine(min);
					}
				}
			}


		}
		return null;
	}

	// Remove min
	public int RemoveMin(){
		if(root == null ){
			return -1;
		}
		BinomialNode temp = findMin();
		BinomialNode prev = null;
		BinomialNode curr = root;
		BinomialNode next = curr.rightSib;
		while(temp!= null){
			if(temp.equals(curr)){
				break;
			}
			prev = curr;
			curr= curr.rightSib;
		}
		//at the head of list
		if(prev == null){
			next = curr.rightSib;
			BinomialNode children = curr.leftChild;
			curr.leftChild= null;
			curr.rightSib = null;
			children = reverse(children,null);
			root= merge(children,next);
			root = combine(root);
		}else if(next == null){
			BinomialNode children = curr.leftChild;
			children = reverse(children,null);
			root= merge(children,prev);
			root = combine(root);

		}else{
			next = curr.rightSib;
			BinomialNode children = curr.leftChild;
			curr.leftChild= null;
			curr.rightSib = null;
			prev.rightSib=next;
			children = reverse(children,null);
			root= merge(children,root);
			//			System.out.println("after merging");
			//printQueue(root,0);
			root = combine(root);
			//			System.out.println("after Combinng");
			//printQueue(root,0);
		}
		size--;
		return temp.value;
	}

	public BinomialNode findMin(){
		BinomialNode temp = root;
		BinomialNode min = temp;
		while(temp!= null){
			if(temp.priority<min.priority){
				min = temp;
			}
			temp = temp.rightSib;
		}
		return min;
	}

	public BinomialNode reverse (BinomialNode currentNode, BinomialNode prev){ 
		if(currentNode == null)
			return null;
		if(currentNode.rightSib ==null) {
			currentNode.rightSib = prev;
			return currentNode;
		}
		BinomialNode next = currentNode.rightSib;
		currentNode.rightSib =prev;
		return reverse(next, currentNode);
	}


	public void decreaseKey(int elem, int newPriority){
		BinomialNode delta = queue[elem];

		delta.priority = newPriority;
		while(delta.parent!=null && delta.parent.priority > delta.priority ){
			int temp = delta.parent.value;
			int priority = delta.parent.priority;
			swap(elem,temp);

			delta.parent.value = delta.value;
			delta.parent.priority = delta.priority;

			delta.value = temp;
			delta.priority = priority;


			delta=delta.parent;
			elem = delta.value;


		}
	}

	public void swap(int pos1, int pos2) {
		BinomialNode tmp;

		tmp = queue[pos1];
		queue[pos1] = queue[pos2];
		queue[pos2] = tmp;
	}

	public void PrintQueue(){
		printQueue(root,0);
	}
	public void printQueue(BinomialNode root, int offset) {

		if (root != null)
		{
			for (int i = 0; i < offset; i++)
				System.out.print("  ");
			System.out.println("Value: " + root.value + "Priority: " + root.priority );
			printQueue(root.leftChild, offset+1);
			printQueue(root.rightSib, offset);
		}
		

	}
	
	public boolean empty(){
//		if(root==null){
//			return true;
//		}
//		return false;
		
		return size == 0;
	}


}