package tree;

import lista.EstruturaDeDados;

public class BST implements EstruturaDeDados{

    private Node root;

    @Override
    public void insert(int key) {
        if (root == null){
            root = new Node(key);
        } else{
            insertNode(root, key);   
        }
    }

    private void insertNode(Node n, int key){
        if (key >= n.getValue()){
            //inserir na direita
            if (n.getRight() == null){
                Node newN = new Node(key);
                n.setRight(newN);
            } else {
                insertNode(n.getRight(), key);
            }
        } else {
            //inserir na esquerda
            if (n.getLeft() == null){
                Node newN = new Node(key);
                n.setLeft(newN);
            } else {
                insertNode(n.getLeft(), key);
            }
        }
    }

    @Override
    public void delete(int chave) {
        if(this.root.getValue() == chave){
            root.setValue(minimum(this.root.getRight()));
            removeMin(this.root);
        } else {
            deleteNode(this.root, chave);
        }
    }

    private void deleteNode(Node n, int key){
        if (key >= n.getValue()){
            Node r = n.getRight();
            if (r.getValue() == key){
                //verificar se r é folha
                if (r.getRight() == null && r.getLeft() == null){
                    //Caso 1
                    n.setRight(null);
                } else if (r.getRight() == null){
                    n.setRight(r.getLeft());
                } else if (r.getLeft() == null){
                    //Caso 2
                    n.setRight(r.getRight());
                } else{
                    // caso 3
                    n.setValue(minimum(n.getRight()));
                    removeMin(n.getRight());
                }
            }else{
                deleteNode(r, key);
            }
        }
        if (key < n.getValue()){
            Node r = n.getLeft();
            if (r.getValue() == key){
                //verificar se r é folha
                if (r.getLeft() == null && r.getLeft() == null){
                    //Caso 1
                    n.setRight(null);
                } else if (r.getLeft() == null){
                    n.setRight(r.getLeft());
                } else if (r.getLeft() == null){
                    //Caso 2
                    n.setRight(r.getLeft());
                } else{
                    // caso 3
                    n.setValue(minimum(n.getRight()));
                    removeMin(n);
                }
            }else{
                deleteNode(r, key);
            }
        }
    }

    private void removeMin(Node n){
        Node y = n.getRight();
        if(y.getLeft() != null){
            Node r = y.getLeft();
            if(r.getLeft() == null){
                y.setLeft(null);
            }else{
                removeMin(r);
            }
        } else {
            Node r = y.getRight();
            n.setRight(r);
        }
    }

    @Override
    public boolean search(int key) {
        if (root == null){
            return false;
        }
        return searchNode(root, key);
    }

    private boolean searchNode(Node n, int key){
        if (n.getValue() == key){
            return true;
        } else if (key > n.getValue()){
            if (n.getRight() == null){
                return false;
            } else {
                return searchNode(n.getRight(),key);
            }
        } else {
            if (n.getLeft() == null){
                return false;
            } else {
                return searchNode(n.getLeft(),key);
            }
        }
    }

    @Override
    public int minimum() {
        if(root == null){
            return 0;
        }
        if(root.getLeft() == null){
            return root.getValue();
        }
        return minimum(root);
    }

    public int minimum(Node n) {
        if(n.getLeft() == null){
            return n.getValue();
        }
        return minimum(n.getRight());
    }
    

    @Override
    public int maximum() {
        if(root == null){
            return 0;
        }
        if(root.getRight() == null){
            return root.getValue();
        }
        return maximum(root);
    }

    public int maximum(Node n){
        if(n.getRight() == null){
            return n.getValue();
        }
        return maximum(n.getRight());
    }

    @Override
    public int sucessor(int chave) {
        if(this.root.getValue() == chave){
            return this.root.getRight().getValue();
        }
        return sucessor(this.root.getRight(), chave);
    }

    public int sucessor(Node n, int chave){
        if(n.getValue() == chave){
            return n.getRight().getValue();
        }
        return sucessor(n.getRight(), chave);
    }

    @Override
    public int prodessor(int chave) {
        if(this.root.getValue() == chave){
            return this.root.getLeft().getValue();
        }
        return prodessor(this.root.getLeft(), chave);
    }

    private prodessor(Node n, int chave){
        if(n.getValue() == chave){
            return n.getLeft().getValue();
        }
        return prodessor(n.getLeft(), chave);
    }

    public static void main(String[] args) {
        BST tree = new BST();
        // System.out.println(tree.search(7));
        tree.insert(4); //root
        tree.insert(2);
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.delete(4); //remove root

        System.out.println(tree.search(4));
        System.out.println(tree.search(5));
        System.out.println(tree.search(6));
        System.out.println(tree.search(7));
        System.out.println(tree.search(3));
        System.out.println(tree.search(7));
        System.out.println("remove 6");
        tree.delete(6); //remove onde só tem 1 filho
        System.out.println(tree.search(5));
        System.out.println("busca 6");
        System.out.println(tree.search(6));
        System.out.println(tree.search(7));
        System.out.println(tree.search(3));
        System.out.println(tree.search(7));
        System.out.println("remove 7");
        tree.delete(7); //remove folha
        System.out.println("busca 5");
        System.out.println(tree.search(5));
        // System.out.println(tree.search(7));
        System.out.println(tree.search(3));
        System.out.println("busca 7");
        System.out.println(tree.search(7));



    }
}
