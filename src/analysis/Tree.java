package analysis;

import java.util.ArrayList;

public class Tree
{
	private TreeNode father;  // ���ڵ�
	private ArrayList<TreeNode> children;  // �����б�
	
	/**
	 * �﷨���Ĺ��캯��
	 * @param father ���ڵ�
	 * @param children �����б�
	 */
	public Tree(TreeNode father, ArrayList<TreeNode> children)
	{
		this.father = father;
		this.children = children;
	}
	
	public TreeNode getFather() 
	{
		return father;
	}
	
	public ArrayList<TreeNode> getChildren() 
	{
		return children;
	}
	

	
	public String toString()
	{
		String result = father.toString() + " -> ";
		for (int i=0; i<children.size(); i++)
		{
			result += children.get(i).toString();
		}
		return result;
	}
	
	



}
