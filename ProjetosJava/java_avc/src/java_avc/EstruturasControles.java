package java_avc;

public class EstruturasControles {

    public static void main(String[] args) {
        int grade = 92;
        if (grade >= 90) {
            System.out.println("Conceito: Ótimo!");
        } else if (grade < 90 && grade >= 70){
            System.out.println("Conceito: Bom!");
        } else if (grade < 70 && grade >= 50){
            System.out.println("Conceito: Suficiente!");
        } else {
            System.out.println("Conceito: Insuficiente!");
        }
    }
}
