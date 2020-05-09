package semantic_analysis;

public class Sym
{
	private String name;  // ������
	private String type;  // ��������
	private int offset;  // ƫ����
	
	/**
	 * ���ű���ÿһ�����ŵĹ��캯��
	 * @param name ������
	 * @param type ��������
	 * @param offset ƫ����
	 */
	public Sym(String name, String type, int offset)
	{
		this.name = name;
		this.type = type;
		this.offset = offset;
	}
	
	public String getName() 
	{
		return name;
	}
	

	
	public String getType() 
	{
		return type;
	}
	

	public int getOffset() 
	{
		return offset;
	}
	

	
	public String toString()
	{
		String result = "(" + name + ",\t" + type + ",\t" + offset + ")";
		return result;
	}
	

}
