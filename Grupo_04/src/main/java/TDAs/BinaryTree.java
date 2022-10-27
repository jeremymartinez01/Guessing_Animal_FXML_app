package TDAs;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {

    private BinaryTreeNode<E> root;

    public BinaryTree(E rootContent) {
        this.root = new BinaryTreeNode<>(rootContent);
    }

    public E getRootContent() {
        return this.root.getContent();
    }

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void setRootContent(E content) {
        this.root = new BinaryTreeNode<>(content);
    }

    private BinaryTreeNode<E> getRoot() {
        return root;
    }

    private void setRoot(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree tree) {
        this.root.setRight(tree);
    }

    public BinaryTree getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree getRight() {
        return this.root.getRight();
    }

    public LinkedList<E> preOrderTraversalRecursive() {
        LinkedList<E> traversal = new LinkedList<>();
        if (!this.isEmpty()) {
            traversal.add(this.getRootContent());
        }
        if (this.getLeft() != null) {
            traversal.addAll(this.getLeft().preOrderTraversalRecursive());
        }
        if (this.getRight() != null) {
            traversal.addAll(this.getRight().preOrderTraversalRecursive());
        }
        return traversal;
    }

    public LinkedList<E> preOrderTraversalIterative() {
        LinkedList<E> traversal = new LinkedList<>();
        Stack<BinaryTree<E>> s = new Stack<>();
        s.push(this);
        while (!s.isEmpty()) {
            BinaryTree<E> tree = s.pop();
            if (!tree.isEmpty()) {
                traversal.add(tree.getRootContent());
            }
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
                s.push(tree.getRight());
            }
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
                s.push(tree.getLeft());
            }
        }
        return traversal;
    }
    
    
    public LinkedList<E> breadthTraversal() {
        LinkedList<E> traversal = new LinkedList<>();
        Queue<BinaryTree<E>> q = new LinkedList<>();
        q.offer(this);
        while (!q.isEmpty()) {
            BinaryTree<E> tree = q.poll();
            if (!tree.isEmpty()) {
                traversal.add(tree.getRootContent());
            }
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
                q.offer(tree.getLeft());
            }
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
                q.offer(tree.getRight());
            }
        }
        return traversal;
    }
    
    //METODOS AGREGADOS
    //devuelve las hojas desde el arbol o subarbol actual
    //implementacion hojas javier
//    public LinkedList<E> listaHojas(){//REVISAR CONDICIONES
//        LinkedList<E> hojas = new LinkedList<>();
//        if(this.getRootContent()==null){
//            //lista vacia
//            return hojas;
//        }else{
//            //compruebo izquierda
//            if(this.getLeft().getRootContent()!=null){
//                this.getLeft().listaHojas();
//            }else{
//                //cuando este en hoja entro aqui
//                if(this.getLeft().getRootContent()==null&&this.getRight().getRootContent()==null){
//                    hojas.add(this.getRootContent());
//                }
//            }
//            //compruebo derecha
//            if(this.getRight().getRootContent()!=null){
//                this.getRight().listaHojas();
//            }else{
//                if(this.getLeft().getRootContent()==null&&this.getRight().getRootContent()==null){
//                    hojas.add(this.getRootContent());
//                }
//            }
//        }
//        
//        
//        return hojas;
//    }
    
        public LinkedList<E> listaHojas(){//REVISAR CONDICIONES
        LinkedList<E> hojas = new LinkedList<>();
        if(this.getRootContent()==null){
            //lista vacia
            return hojas;
        }else if(this.getRootContent()!= null && this.getLeft()==null && this.getRight()==null){
            //si la raiz ya es hoja
            hojas.add(this.getRootContent());
            
        }
        if(this.getLeft()!=null){
            this.getLeft().listaHojas();
        }
        if(this.getRight()!=null){
            this.getRight().listaHojas();
        }
//        else{
//            //compruebo izquierda
//            if(this.getLeft()!=null){
//                this.getLeft().listaHojas();
//            }else{
//                //cuando este en hoja entro aqui
//                if(this.getLeft()==null&&this.getRight()==null){
//                    hojas.add(this.getRootContent());
//                }
//            }
//            //compruebo derecha
//            if(this.getRight()!=null){
//                this.getRight().listaHojas();
//            }else{
//                if(this.getLeft()==null&&this.getRight()==null){
//                    hojas.add(this.getRootContent());
//                }
//            }
//        }
        
        
        return hojas;
    }
    
    public LinkedList<E> getHojas(){
        LinkedList<E> hojas = new LinkedList<>();
        if(root== null){
            return hojas;
        }
        else if(root.getLeft()==null && root.getRight()==null){
            hojas.add(root.getContent());
        }else{
            if(root.getLeft()!=null){
            hojas.addAll(root.getLeft().getHojas());
            }
            if(root.getRight()!=null){
            hojas.addAll(root.getRight().getHojas());
            }
           
        }
        return hojas;
        

    }
    public boolean recursiveSearch(E content, Comparator<E> cmp) {

        boolean result =false;
        if (!this.isEmpty()) {
            if (cmp.compare(content, this.getRootContent()) == 0) {
                return true;
            } else {

                if (this.getLeft() != null) {
                    result = result || this.getLeft().recursiveSearch(content, cmp);
                }

                if (this.getRight() != null) {
                    result = result || this.getRight().recursiveSearch(content, cmp);
                }
            }
        }
        return result;
    }

}
