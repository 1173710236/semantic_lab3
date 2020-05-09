package analysis;

import java.util.ArrayList;

public class DFA
{
	// ��Ŀ�����,��DFA״̬��
	public int id;  
	// DFA�е�״̬���б�,ÿ��Ԫ�ر�ʾһ������ʽ״̬
	public ArrayList<ProS> set = new ArrayList<ProS>();
	
	/**
	 * һ��DFA״̬
	 * @param id ��Ŀ�����,��DFA״̬��
	 */
	public DFA(int id)
	{
		this.id = id;
	}
	
	public boolean contains(ProS lrd)
	{
		for(ProS l:set)
		{
			if(l.equalTo(lrd))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean addNewDerivation(ProS d)
	{
		if(contains(d))
		{
			return false;
		} 
		else 
		{
			set.add(d);
			return true;
		}
	}
	
	/**
	 * ����һ����Ŀ��������"."����ķ���
	 * @return ����һ����Ŀ��������"."����ķ���
	 */
	public ArrayList<String> getGotoPath()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(ProS lrd:set)
		{
			if(lrd.d.list.size()==lrd.index)  // ��Լ״̬
			{
				continue;
			}
			String s = lrd.d.list.get(lrd.index);  // "."����ķ���
			if(!result.contains(s))
			{
				result.add(s);
			}
		}
		return result;
	}
	
	/**
	 * ����һ����Ŀ���У�s����Ŀ��"."����ķ�����ƥ��ģ����ڵĲ���ʽ״̬�б�
	 * @param s ��������ķ���
	 * @return ����ʽ״̬�б�
	 */
	public ArrayList<ProS> getLRDs(String s)
	{
		ArrayList<ProS> result = new ArrayList<ProS>();
		for(ProS lrd:set)
		{
			if(lrd.d.list.size() != lrd.index)  // �ǹ�Լ״̬
			{
				String s1 = lrd.d.list.get(lrd.index);
				if(s1.equals(s))
				{
					result.add((ProS)lrd.clone());
				}
			}
		}
		return result;
	}
	
	public boolean equalTo(DFA state)
	{
		if(this.toString().hashCode()==state.toString().hashCode())
		{

			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	public String toString()
	{
		String result = "";
		for(int i = 0;i < set.size();i++)
		{
			result += set.get(i);
			if(i < set.size()-1)
			{
				result += "\n";
			}
		}
		return result;
	}
	

}
