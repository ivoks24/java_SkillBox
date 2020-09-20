package single_linked_list;

public class SingleLinkedList {
    private ListItem top;

    public void push(ListItem item) {

        if(top != null) {
            item.setNext(top);
        }
        top = item;
    }

    public ListItem pop() {

        ListItem item = top;
        if(top != null)
        {
            top = top.getNext();
            item.setNext(null);
        }
        return item;
    }

    public void removeTop() {

        if(top != null) {
            top = top.getNext();
        }
    }

    public void removeLat() {

        if (top != null) {

            ListItem prev = top;
            ListItem item = top.getNext();
            if (item != null) {
                while (item.getNext() != null) {
                    prev = item;
                    item = item.getNext();
                }
                prev.setNext(null);
            } else {
                top = null;
            }

        }
    }

}