package Stockage;

import java.util.ArrayList;
import java.util.LinkedList;

public class Paquet {

  long id ;
  int power ;
  LinkedList<Machine> otherHosts ;
  Machine owner ;
  
  Paquet(long Id, int p , LinkedList<Machine> others, Machine proprio) {
    id = Id ;
    power = p ;
    otherHosts = others ;
    owner = proprio ;
  }
  
  public static LinkedList<ArrayList<Paquet>> fileToPaquets(String path){  //doit d�couper un fichier en liste de groupes de (4+1) paquets
    return null ;
  }
  
}