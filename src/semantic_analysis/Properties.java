package semantic_analysis;

import java.util.ArrayList;
import java.util.List;

public class Properties
{
	private String name;  // �������ߺ�����name 
	private String type;  // �ڵ�����
	private String offset;  // �������͵�����
	private int width;  // ���ʹ�С����
	
	private Array array;  // ������������
	
	private String addr;  // ���ʽ���͵�����

	private int quad;  // �����õ�������,ָ��λ��	
	private List<Integer> truelist = new ArrayList<Integer>();  // �б�
	private List<Integer> falselist = new ArrayList<Integer>();  // �б� 
	private List<Integer> nextlist = new ArrayList<Integer>();  // �б�  

	
	public String getName()
	{
		return name;
	}

	public Array getArray()
	{
		return array;
	}
	
	public String getAddr()
	{
		return addr;
	}

	public String getType()
	{
		return type;
	}

	public String getOffset()
	{
		return offset;
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getQuad()
	{
		return quad;
	}
	
	public List<Integer> getNext()
	{
		return nextlist;
	}

	public List<Integer> getTrue()
	{
		return truelist;
	}

	public List<Integer> getFalse()
	{
		return falselist;
	}

	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setArray(Array array)
	{
		this.array=array;
	}

	public void setAddr(String addr)
	{
		this.addr=addr;
	}

	public void setType(String type)
	{
		this.type=type;
	}

	public void setOffset(String offset)
	{
		this.offset=offset;
	}
	
	public void setWidth(int width)
	{
		this.width=width;
	}
	
	public void setQuad(int quad)
	{
		this.quad=quad;
	}

	public void setNext(List<Integer> nextlist)
	{
		this.nextlist=nextlist;
	}

	public void setTrue(List<Integer> truelist)
	{
		this.truelist=truelist;
	}

	public void setFalse(List<Integer> falselist)
	{
		this.falselist=falselist;
	}
	
	

		
}
