package semantic_analysis;

public class Sym
{
	private String name;  // 符号名
	private String type;  // 符号类型
	private int offset;  // 偏移量
	
	/**
	 * 符号表中每一个符号的构造函数
	 * @param name 符号名
	 * @param type 符号类型
	 * @param offset 偏移量
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
