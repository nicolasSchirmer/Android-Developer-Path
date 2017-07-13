package nschirmer.schirmer.aula04_instagram;

public class Item {
    private int avatarID, photoID;
    private String nome = "";

    public Item(int avatarID, int photoID, String nome) {
        this.avatarID = avatarID;
        this.photoID = photoID;
        this.nome = nome;
    }

    public int getAvatarID() {
        return avatarID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public String getNome() {
        return nome;
    }
}
