
public class TermNode {
    protected Term data;
    protected TermNode next;

    public TermNode(Term data){
        this.data = data;
        this.next = null;
    }

    public void setNext(TermNode next){
        this.next = next;
    }

    public TermNode getNext(){
        return next;
    }

    public Term getData(){
        return data;
    }

    public String toString(){
        StringBuilder termString = new StringBuilder();
        int coefficient = data.coefficient;
        int degreeX = data.degreeX;
        int degreeY = data.degreeY;
        int degreeZ = data.degreeZ;

        if (coefficient != 0) {
            if (coefficient > 0) {
                termString.append("+");
            }
            termString.append(coefficient);

            if (degreeX != 0) {
                termString.append("x");
                if (degreeX != 1) {
                    termString.append(degreeX);
                }
            }

            if (degreeY != 0) {
                termString.append("y");
                if (degreeY != 1) {
                    termString.append(degreeY);
                }
            }

            if (degreeZ != 0) {
                termString.append("z");
                if (degreeZ != 1) {
                    termString.append(degreeZ);
                }
            }
        }
        return termString.toString();
    }
}
