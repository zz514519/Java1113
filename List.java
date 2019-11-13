/**
 * Author : Zhang Zhe
 */
class Listlink{
    public int data;
    public  Listlink next;
    public Listlink(int data){
        this.data=data;
        this.next=null;
    }
}
class List {
    public Listlink head;

    public List() {
        this.head = null;
    }

    //头插法
    public void addFirst(int data) {
        Listlink node = new Listlink(data);
        if (this.head == null) {
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
    }

    //尾插法
    public void addLast(int data) {
        Listlink node = new Listlink(data);
        Listlink cur = this.head;
        if (this.head == null) {
            this.head = node;
        } else {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    //打印单列表
    public void display() {
        Listlink cur = this.head;
        if (this.head == null) {
            return;
        } else {
            while (cur != null) {
                System.out.print(cur.data + "    ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    //获取链表长度
    public int getlength() {
        Listlink cur = this.head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //插入到index位置
    //任意位置插入，第一个数据节点为0号下标
    public boolean addIndex(int index, int data) {
        if (index < 0 || index > getlength()) {
            return false;
        }
        if (index == 0) {
            addFirst(data);
            return true;
        } else {
            Listlink node = new Listlink(data);
            Listlink cur = this.head;
            int count = 0;
            while (count < index - 1) {
                cur = cur.next;
                count++;
            }
            node.next = cur.next;
            cur.next = node;
        }
        return true;
    }
    //查询是否含有key
    public boolean contains(int key){
        Listlink cur = this.head;
        while (cur!=null){
            if(cur.data==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }
    //找前驱
    public Listlink seachPrev(int key){
        Listlink cur = this.head;
        while (cur.next!=null){
            if(cur.next.data==key){
                return cur;
            }
            cur=cur.next;
        }
        return null;
    }
    public void remove(int key){
        Listlink cur = seachPrev(key);
        if(cur==null){
            System.out.println("没有要删除的节点！");
            return ;
        }
        if(this.head.data==key){
            this.head=this.head.next;
        }
        Listlink del = cur.next;
        cur.next=del.next;
    }
    public void removeAll(int key){
        Listlink cur = this.head;
        Listlink prev = this.head.next;
        while(prev!=null){
            if(cur.next.data==key){
                cur.next=prev.next;
                prev=prev.next;
            }else{
                cur=prev;
                prev=prev.next;
            }
        }
        if(this.head.data==key){
            this.head = this.head.next;
        }
    }
    //逆序单列表
    public Listlink reverseList(){
        Listlink cur=this.head;     //当前需要反转的节点
        Listlink prev=null;
        Listlink newHead=null;
        while (cur != null) {
            Listlink curNext=cur.next;
            if(curNext==null){
                newHead=cur;
            }
            cur.next=prev;
            prev=cur;
            cur=curNext;
        }
        return newHead;
    }
    //打印逆序单列表
    public void play(){
        Listlink cur=reverseList();
        if(cur==null){
            return;
        }
        while(cur!=null){
            System.out.print(cur.data+"    ");
            cur=cur.next;
        }
        System.out.println();
    }
    //返回中间值
    public Listlink middleNode(){
        Listlink fast = this.head;
        Listlink slow = this.head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    //寻找倒数第K个节点
    public Listlink findKthToTail(int k) {
        Listlink fast = this.head;
        Listlink slow = this.head;
        if (k <= 0) {
            return null;
        }
        while (k - 1 > 0) {
            if (fast.next != null) {
                fast = fast.next;
                k--;
            } else {
                System.out.println("没有这个节点！");
                return null;
            }
        }
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
    //以X为基准分割链表
    public Listlink partition(int x){
        Listlink bs = null;
        Listlink be = null;
        Listlink as = null;
        Listlink ae = null;

        Listlink cur = this.head;
        while(cur!=null){
            if(cur.data<x){
                //是不是第一次插入
                if(bs==null){
                    bs=cur;
                    be=bs;
                }else{
                    be.next=cur;
                    be=be.next;
                }
            }else{
                //是不是第一次插入
                if(as==null){
                    as=cur;
                    ae=cur;
                }else{
                    ae.next=cur;
                    ae=ae.next;
                }
            }
            cur=cur.next;
        }
        if(bs==null){
            return as;
        }
        be.next=as;
        if(as!=null){
            ae.next=null;
        }
        return  bs;
    }
    //删除重复的节点
    public Listlink deleteDuplication(){
        if(this.head==null){
            return null;
        }
        Listlink cur = this.head;
        Listlink newHead = new Listlink(-1);
        Listlink tmp = newHead;
        while (cur!=null){
            //重复的节点
            if(cur.next!=null&&cur.data==cur.next.data){
                //每次都需要判断
                while (cur.next!=null&&cur.data==cur.next.data){
                    cur=cur.next;
                }
                cur=cur.next;
            }else {
                tmp.next=cur;
                tmp=tmp.next;
                cur=cur.next;
            }
        }
        //最后一个节点如果也是重复的要将tmp.next置为null
        tmp.next=null;
        return newHead.next;
    }
    //回文结构
    public boolean chkPalindrome(){
        if(this.head==null) {
            return false;
        }
        if(this.head.next==null){
            return true;
        }
        //1.找单列表中间节点
        Listlink fast = this.head;
        Listlink slow = this.head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        //2.反转单列表
        Listlink cur = slow.next;
        while(cur!=null){
            Listlink curNext=cur.next;
            cur.next=slow;
            slow=cur;
            cur=curNext;
        }
        //3.fast/slow往前head往后走
        while(slow!=this.head){
            if(slow.data==this.head.data){
                if(this.head.next==slow){
                    return true;
                }
                slow=slow.next;
                this.head=this.head.next;
            }else {
                return false;
            }
        }
        return true;
    }
    //自创环
    public void creteLoop(){
        Listlink cur = this.head;
        while (cur.next!=null){
            cur=cur.next;
        }
        cur.next=this.head.next.next;
    }
    //判断是否有环
    public boolean hasCycle(){
        Listlink fast = this.head;
        Listlink slow = this.head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                break;
            }
        }
        if(fast==null||fast.next==null){
            return false;
        }
        return true;
    }
    //开始入环的第一个节点。
    public Listlink detectCycle(){
        Listlink fast = this.head;
        Listlink slow = this.head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                break;
            }
        }
        if(fast==null||fast.next==null){
            return null;
        }
        slow=this.head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    //合并两个有序单列表
    public static Listlink mergeTwoLists(Listlink headA, Listlink headB){
        Listlink newHead = new Listlink(-1);
        Listlink tmp = newHead;
        while(headA!=null&&headB!=null){
            if(headA.data<headB.data){
                tmp.next=headA;
                headA=headA.next;
                tmp=tmp.next;
            }else{
                tmp.next=headB;
                headB=headB.next;
                tmp=tmp.next;
            }
        }
        if(headA==null){
            tmp.next=headB;
        }else{
            tmp.next=headA;
        }
        return newHead.next;
    }
    //找相交节点
    public static Listlink getIntersectionlink(Listlink headA,Listlink headB){
        if(headA==null||headB==null){
            return null;
        }

        Listlink pL = headA;//永远指向长的单列表
        Listlink pS = headB;//永远指向短的单列表

        int lenA = 0;
        int lenB = 0;

        //求lenA，冷lenB
        while(pL!=null){
            lenA++;
            pL = pL.next;
        }
        while(pS!=null){
            lenB++;
            pS = pS.next;
        }
        pL=headA;
        pS=headB;
        //差值-》最长的单链表先走len步
        int len = lenA-lenB;
        if(len < 0) {
            pL = headB;
            pS = headA;
            len = lenB-lenA;
        }
        //让pL先走len步
        while (len>0){
            pL=pL.next;
            len--;
        }
        //开始一起走  (pL  != pS ) {一人一步走}
        while(pL!=pS){
            pL=pL.next;
            pS=pS.next;
        }
        return pL;
    }
}