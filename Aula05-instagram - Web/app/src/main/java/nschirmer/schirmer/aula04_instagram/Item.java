package nschirmer.schirmer.aula04_instagram;

public class Item {

    private String nome = "", avatarUrl = "", photoUrl = "";

    public Item(String nome, String avatarUrl, String photoUrl) {
        this.nome = nome;
        this.avatarUrl = avatarUrl;
        this.photoUrl = photoUrl;
    }

    public String getNome() {
        return nome;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
