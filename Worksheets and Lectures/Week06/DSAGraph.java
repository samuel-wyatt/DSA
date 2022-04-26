public class DSAGraph {
    private class DSAGraphVertex {
        //Class Fields
        int label, links, visited;
        Object value;
        //Constructor
        public DSAGraphVertex(int inLabel, Object inValue) {
            this.label = inLabel;
            this.value = inValue;
            links = 0;
            visited = 0;
        }

        //Accessors
        int getLabel() {
            return this.label;
        }
        Object getValue() {
            return this.value;
        }
        
    }

    //Class Fields


    //Constructor
    public DSAGraph() {

    }

    //Methods
    public void addVertex()
}