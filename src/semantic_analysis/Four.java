package semantic_analysis;


public class Four
{
	private String op;  // ������
	private String param1;  // ����һ
	private String param2;  // ������
	private String toaddr;  // ��ַ
	
	/**
	 * ��Ԫʽ���캯��
	 * @param op ������
	 * @param param1 ����һ
	 * @param param2 ������
	 * @param toaddr ��ַ
	 */
	public Four(String op, String param1, String param2, String toaddr)
	{
		this.op = op;
		this.param1 = param1;
		this.param2 = param2;
		this.toaddr = toaddr;
	}
	

	
	public void setToaddr(String toaddr) 
	{
		this.toaddr = toaddr;
	}

	public String toString()
	{
		String result = "(" + op + ",\t" + param1 + ",\t" + param2 + ",\t" + toaddr + ")\t";
		return result;
	}
	
}
