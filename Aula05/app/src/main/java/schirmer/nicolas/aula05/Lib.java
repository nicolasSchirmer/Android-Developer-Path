package schirmer.nicolas.aula05;


public class Lib {

    int imgs[] = {R.drawable.photo_azelha, R.drawable.photo_2, R.drawable.catau, R.drawable.correr, R.drawable.direito};
    int names[] = {R.string.knot1, R.string.knot2, R.string.knot3, R.string.knot4, R.string.knot5};

    public int getSize(){
        return imgs.length;
    }

    public int getImgID(int pos){
        return imgs[pos];
    }

    public int getNameID(int pos){
        return names[pos];
    }


}
