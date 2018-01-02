package com.aciw.foobar.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * DO NOT MODIFY
 * 
 * Provided to participants when attempting to solve the challenge.
 * 
 * This class provides an overridden toString() which will need to be hashed by participants 
 * to produce new nodes. When creating these new nodes, the id will need to be incremented
 * as well as the children that belong to the new node.
 * 
 * It should be noted that the id should be incremented left to right as the tree is generated
 * from bottom up. it is important to id the nodes properly as this will affect all future hashing.
 * This idea is illustrated below. Each number represents the id for a node with the top one being
 * the root node.
 * 
 *		________|________		
 * _____|____	6	____|____
 * |	4	|		|	5	|
 * 0		1		2		3
 * 
 * @author lodovicai
 *
 */
public class Node implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String hash;
	ArrayList<Node> children = new ArrayList<Node>();

	public Node() {

	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash.toLowerCase();
	}

	public Node(int id, String hash) {
		super();
		this.id = id;
		this.hash = hash.toLowerCase();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id &&
                Objects.equals(hash, node.hash) && Objects.equals(children, node.children);
    }

	/**
	 * Overriden toString to show a more readable description of the node object itself and 
	 * prevent recursive prints of children objects.
	 */
	@Override
	public String toString() {
		// check children
		StringBuilder childs = new StringBuilder();
		if (children != null) {
			for (int i = 0; i < children.size(); i++) {
				childs.append(String.valueOf(children.get(i).getId()));

				// add separator if we still expect another child
				if (i < children.size()) {
					childs.append("|");
				}
			}
		}

		return String.format("Node [id=" + id + ", hash=" + hash + ", child=" + childs.toString() + "]\n");
	}

}

