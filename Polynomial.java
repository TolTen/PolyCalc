
public class Polynomial {

    protected TermNode head;
    protected TermNode tail;

    public Polynomial() {
        head = null;
        tail = null;
    }

    //Eşlenik toplamaya çalıştım patladı. Onu çöz
    public Polynomial add(Polynomial polynomial){
        TermNode i, j, k, node;
        Polynomial result;
        int coefficient, degreeX, degreeY, degreeZ;
        i = head;
        j = polynomial.head;
        result = new Polynomial();
        while (i != null && j != null){
            if (i.data.degreeX == j.data.degreeX && i.data.degreeY == j.data.degreeY && i.data.degreeZ == j.data.degreeZ){
                coefficient = i.data.coefficient + j.data.coefficient;
                degreeX = i.data.degreeX;
                degreeY = i.data.degreeY;
                degreeZ = i.data.degreeZ;
                i = i.next;
                j = j.next;
            } else
            if (i.data.degreeX > j.data.degreeX || (i.data.degreeX == j.data.degreeX && i.data.degreeY > j.data.degreeY) || (i.data.degreeX == j.data.degreeX && i.data.degreeY == j.data.degreeY && i.data.degreeZ > j.data.degreeZ)){
                coefficient = i.data.coefficient;
                degreeX = i.data.degreeX;
                degreeY = i.data.degreeY;
                degreeZ = i.data.degreeZ;
                i = i.next;
            } else {
                coefficient = j.data.coefficient;
                degreeX = j.data.degreeX;
                degreeY = j.data.degreeY;
                degreeZ = j.data.degreeZ;
                j = j.next;
            }
            if (coefficient != 0){
                node = new TermNode(new Term(coefficient, degreeX, degreeY, degreeZ));
                result.insertLast(node);
            }
        }
        if (i == null)
            k = j;
        else
            k = i;
        while (k != null){
            node = new TermNode(new Term(k.getData().coefficient, k.getData().degreeX, k.getData().degreeY, k.getData().degreeZ));
            result.insertLast(node);
            k = k.next;
        }
        return result;
    }

    //Sorun yok gibi duruyor-SUBTRACTION METODU İLE İLGİLİ
    public Polynomial subtraction(Polynomial polynomial) {
        TermNode i, j, k, node;
        Polynomial result;
        int coefficient, degreeX, degreeY, degreeZ;
        i = head;
        j = polynomial.head;
        result = new Polynomial();
        while (i != null && j != null) {
            if (i.data.degreeX == j.data.degreeX && i.data.degreeY == j.data.degreeY && i.data.degreeZ == j.data.degreeZ) {
                coefficient = i.data.coefficient - j.data.coefficient;
                degreeX = i.data.degreeX;
                degreeY = i.data.degreeY;
                degreeZ = i.data.degreeZ;
                i = i.next;
                j = j.next;
            } else if (i.data.degreeX > j.data.degreeX || (i.data.degreeX == j.data.degreeX && i.data.degreeY > j.data.degreeY) || (i.data.degreeX == j.data.degreeX && i.data.degreeY == j.data.degreeY && i.data.degreeZ > j.data.degreeZ)) {
                coefficient = i.data.coefficient;
                degreeX = i.data.degreeX;
                degreeY = i.data.degreeY;
                degreeZ = i.data.degreeZ;
                i = i.next;
            } else {
                coefficient = -j.data.coefficient; // Çıkarma işlemi burada yapılır
                degreeX = j.data.degreeX;
                degreeY = j.data.degreeY;
                degreeZ = j.data.degreeZ;
                j = j.next;
            }
            if (coefficient != 0) {
                node = new TermNode(new Term(coefficient, degreeX, degreeY, degreeZ));
                result.insertLast(node);
            }
        }
        // i polinomunun sonuna ulaşıldığında, j polinomundaki tüm terimlerin negatif alınması gerekir
        // çünkü i polinomu bittiğinde, j polinomu ile çıkarma işlemi tamamlanmamıştır
        while (j != null) {
            coefficient = -j.data.coefficient;
            degreeX = j.data.degreeX;
            degreeY = j.data.degreeY;
            degreeZ = j.data.degreeZ;
            node = new TermNode(new Term(coefficient, degreeX, degreeY, degreeZ));
            result.insertLast(node);
            j = j.next;
        }
        return result;
    }

    //SUBTRACTION METODU İLE İLGİLİ
    //Çıkan sonucu da toplayıp simplify etmen gerekiyor yoksa birebir aynı outputu vermiyor.
    public Polynomial multiplication(Polynomial polynomial){
        Polynomial result = new Polynomial();
        TermNode i = this.head;

        while (i != null){
            TermNode j = polynomial.head;
            while (j != null){
                Polynomial product = new Polynomial();
                TermNode node = new TermNode(new Term(i.data.coefficient * j.data.coefficient,
                        i.data.degreeX + j.data.degreeX,
                        i.data.degreeY + j.data.degreeY,
                        i.data.degreeZ + j.data.degreeZ));
                product.head = node;
                product.tail = node;
                result = result.add(product);
                j = j.next;
            }

            i = i.next;
        }

        return result;
    }


    public void insertLast(TermNode newTermNode) {
        if (head == null) {
            head = newTermNode;
        } else {
            tail.setNext(newTermNode);
        }
        tail = newTermNode;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        TermNode tmp = head;
        while (tmp != null) {
            result.append(tmp).append("");
            tmp = tmp.getNext();
        }

        if (result.length() == 0){
            result.append("0");
        }

        return result.toString();
    }
}
