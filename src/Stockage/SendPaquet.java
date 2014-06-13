package Stockage;

import java.util.ArrayList;

public class SendPaquet {

  public static void envoieData(ArrayList<Paquet> listPaquets, ArrayList<Machine> hosts){
    //envoie un groupe de paquets � un groupe d'hosts
    //stock les paquets re�us en �change dans d
    int n = listPaquets.size() ; //should equal 5
    assert n == hosts.size() ;
    for (int j = 0 ; j< n ; j++){
      Paquet p = listPaquets.get(j) ;
      p.putOtherHosts(hosts);
      putAndGet(hosts.get(j),p) ;
      Donnees.addHost(hosts.get(j)) ;
    }
  }
  
  public static void putAndGet(Machine m, Paquet p){ //TODO
    //cette m�thode actualise le champ myData de Donnees
    //Envoie UN paquet � m et re�oit UN paquet en �change
  }
  
  public static void prevenirHostChanged(long Id){  //TODO
    //pr�viens une machine que cette machine remplace m pour le paquet d'id Id
    Paquet p = Donnees.getHostedPaquet(Id) ;
    int placeToModify = p.power ;
    for (int i =0 ; i< 5 ; i++) {
      if (i!=placeToModify){
        Machine m = p.otherHosts.get(i) ;
        //envoieMsg(m,placeToModify) pour changement d'host
      }
    }
  }
  
}


