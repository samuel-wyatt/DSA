public class DSAQueue {

    int DEFAULT_CAPACITY = 100;
    int count;
    Object[] queue;

    public int getCount() {
        return 0;
    }
    public boolean isEmpty() {
        return true;
    }
    public boolean isFull() {
        return true;
    }
    public Object peek() {
        return 0;
    }
    public void display() {}
}

class ShufflingQueue extends DSAQueue {

    public ShufflingQueue() {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    public ShufflingQueue(int maxCapacity) {
        queue = new Object[maxCapacity];
        count = 0;
    }

    public void enqueue(Object value) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Queue is full.");
        } else {
            queue[count] = value;
            count += 1;
        }
    }

    public Object dequeue() {
        Object value = queue[0];
        if (count == 0) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        } else {
            value = queue[0];
            for (int i = 0; i < queue.length - 1; i++) {
                queue[i] = queue[i + 1];
            }
            count -= 1;
        }   
        return value;
    }

    public int getCount() {
        return this.count;
    }

    public boolean isEmpty() {
        boolean empty = false;
        if (count == 0) {
            empty = true;
        }
        return empty;
    }

    public boolean isFull() {
        boolean full = false;
        if (count == queue.length) {
            full = true;
        }
        return full;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty.");
        } else {
            return queue[0];
        }
    }

    public void display() {
        System.out.println("________Queue Content________");
        for (int i = 0; i < queue.length; i++) {
            System.out.print("\t" + queue[i]);
        }
        System.out.println("\n_____________________________");
    }
}

class CircularQueue extends DSAQueue {

    private int front = -1;
    private int back = -1;
    
    public CircularQueue() {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    public CircularQueue(int maxCapacity) {
        queue = new Object[maxCapacity];
        count = 0;
    }

    public void enqueue(Object value) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Queue is full.");
        } else {
            if (front == -1) {
                front = 0;
            }
        back = (back + 1) % queue.length;
        queue[back] = value;
        count = front + 1;
        }
    }

    public Object dequeue() {
        Object value;
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        } else {
            value = queue[front];  
            queue[front] = 0;
            if (front == back) {
                front = -1;
                back = -1;
            } else {
                front = (front + 1) % queue.length;
            }
        }
        count += 1;
        return value;
    }

    public int getCount() {
        return this.count;
    }

    public boolean isEmpty() {
        return front == -1 ? true : false;
    }

    public boolean isFull() {
        return (front == (back + 1) % queue.length) ? true : false;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty.");
        } else {
            return queue[front];
        }
    }

    public void display() {
        System.out.println("________Queue Content________");
        for (int i = 0; i < queue.length; i++) {
            System.out.print("\t" + queue[i]);
        }
        System.out.println("\n_____________________________");
    }
}