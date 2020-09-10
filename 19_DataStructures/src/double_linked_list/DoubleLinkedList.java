package double_linked_list;

public class DoubleLinkedList {

    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement() {
        return head;
    }

    public ListItem getTailElement() {
        return tail;
    }

    public ListItem popHeadElement() {
        return head;
    }

    public ListItem popTailElement() {
        return tail;
    }

    public void removeHeadElement() {
//        ListItem item = head;
//        if (head != null) {
//        }
        head = null;
    }

    public void removeTailElement() {
        tail = null;
    }

    public void addToHead(ListItem item) {
        head = item;

    }

    public void addToTail(ListItem item) {
        tail = item;
    }
}