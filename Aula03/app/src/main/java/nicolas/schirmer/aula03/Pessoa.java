package nicolas.schirmer.aula03;

public class Pessoa {

    private String nome;
    private int imgRes;

    public Pessoa(String nome, int imgRes) {
        this.nome = nome;
        this.imgRes = imgRes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
