//package controller;
//
//import entity.Lampadina;
//import entity.Presa;
//import entity.Sistema;
//import ui.SistemaLayer;
//
//import java.awt.event.*;
//
//public class UserListener extends MouseAdapter {
//    private final Sistema sistema;
//    public UserListener(Sistema sistema) {
//        this.sistema = sistema;
//    }
//
//    private boolean haCliccatoSu(Presa p,MouseEvent e){
//        float lx = p.getX();
//        float ly = p.getY();
//        float rx = p.getX() + p.getImmagine().getWidth();
//        float ry = p.getY() + p.getImmagine().getHeight();
//        return e.getX() >= lx && e.getX() <= rx && e.getY() >= ly && e.getY() <= ry;
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        super.mouseClicked(e);
//        for(Presa p : sistema.getPrese()){
//            if(haCliccatoSu(p,e)){
//                if(e.getButton() == MouseEvent.BUTTON3) {
//                    p.aggiungiLampadina(new Lampadina("123",123));
//                } else if(e.getButton() == MouseEvent.BUTTON1) {
//                    p.togliLampadina();
//                } else {
//                    continue;
//                }
//                break;
//            }
//        }
//        SistemaLayer.disegnaPrese();
//    }
//}
