package double_linked_list;

public class DoubleLinkedList
{
    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement()
    {
        return head;
    }

    public ListItem getTailElement()
    {
        return tail;
    }

    public ListItem popHeadElement()
    {
        ListItem item = head;
        if (head != null){
            head = head.getNext();
            if (head != null){
                head.setPrev(null);
            } else {
                tail = null;
            }
            item.setNext(null);
        }
        return item;
    }

    public ListItem popTailElement()
    {
        ListItem item = tail;
        if (tail != null){
            tail = tail.getPrev();
            if (tail != null){
                tail.setNext(null);
            } else {
                head = null;
            }
            item.setPrev(null);
        }
        return item;
    }

    public void removeHeadElement()
    {
        if (head != null){
            head = head.getNext();
            if (head != null){
                head.setPrev(null);
            } else {
                tail = null;
            }
        }
    }

    public void removeTailElement()
    {
        if (tail != null){
            tail = tail.getNext();
            if (tail != null){
                tail.setNext(null);
            }else {
                head = null;
            }
        }
    }

    public void addToHead(ListItem item)
    {
        item.setNext(head);
        if (head != null){
            head.setPrev(item);
        } else {
            tail = item;
        }
        head = item;
    }

    public void addToTail(ListItem item)
    {
        item.setPrev(tail);
        if (tail != null){
            tail.setNext(item);
        } else {
            head = item;
        }
        tail = item;
    }
}