import java.awt.*;
import java.util.ArrayList;

public class BeadFinder {
    // NIMA SAEIDI / SANA GHORBANI / SANA GHOLINAVAZ / BITA RAZPOOR
    private double tau;
    ArrayList<Blob> blobs=new ArrayList<>();
    Picture image;
    public BeadFinder(Picture picture, double tau) {
        this.tau = tau;
        image=picture;

       for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                if (picture.get(i, j).getBlue() >= tau && picture.get(i, j).getGreen() >= tau && picture.get(i, j).getRed() >= tau) {
                    image.set(i, j, Color.white);

                } else {
                    image.set(i , j, Color.black);
                }

            }
        }

        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                if ((image.get(j,i)).equals(Color.white)){
                    Blob blob=new Blob();
                    finder(blob,i,j);
                    blobs.add(blob);

                }
            }
        }
    }
    private void finder(Blob blob, int i , int j){
        image.set(j,i,new Color(0,0,0));
        blob.add(i,j);
        if (i<image.height()-1){
            if ((image.get(j,i+1)).equals(new Color(255,255,255)))
            finder(blob,i+1,j);
        }
        if (j>0){
            if ((image.get(j-1,i)).equals(new Color(255,255,255)))
                finder(blob,i,j-1);
        }if (i>0){
            if ((image.get(j,i-1)).equals(new Color(255, 255, 255)))
                finder(blob,i-1,j);
        }if (j<image.width()-1){
            if ((image.get(j+1,i)).equals(new Color(255,255,255)))
                finder(blob,i,j+1);

        }/*if (j<639&&i<479) {
            if ((image.get(j + 1, i)).equals(Color.white))
                finder(blob, i + 1, j + 1);
        }if (j<639&&i>0) {
            if ((image.get(j + 1, i)).equals(Color.white))
                finder(blob, i - 1, j + 1);
        }if (j>0&&i<479) {
            if ((image.get(j + 1, i)).equals(Color.white))
                finder(blob, i + 1, j - 1);
        }if (j>0&&i>0) {
            if ((image.get(j + 1, i)).equals(Color.white))
                finder(blob, i - 1, j - 1);
        }

*/
    }
    public ArrayList<Blob> getBeads(int min) {
        ArrayList<Blob> findBlob=new ArrayList<>();
        for (Blob blob :blobs) {
            if (blob.mass()>min)
                findBlob.add(blob);
        }
        return findBlob;
    }
    public static void main(String[] args) {
        int P=Integer.parseInt(args[0]);
       double tau=Integer.parseInt(args[1]);
       Picture picture=new Picture(args[2]);
       BeadFinder find=new BeadFinder(picture,tau);
       ArrayList<Blob> tmp=find.getBeads(P);
        for (Blob blob : tmp) {
            System.out.format(blob.ToString());
            System.out.println();
        }

        }


    }
        


