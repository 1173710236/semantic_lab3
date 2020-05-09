package analysis;

import java.util.ArrayList;

public class DFA
{
	// 项目集编号,即DFA状态号
	public int id;  
	// DFA中的状态集列表,每个元素表示一个产生式状态
	public ArrayList<ProS> set = new ArrayList<ProS>();
	
	/**
	 * 一个DFA状态
	 * @param id 项目集编号,即DFA状态号
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
	 * 返回一个项目集中所有"."后面的符号
	 * @return 返回一个项目集中所有"."后面的符号
	 */
	public ArrayList<String> getGotoPath()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(ProS lrd:set)
		{
			if(lrd.d.list.size()==lrd.index)  // 规约状态
			{
				continue;
			}
			String s = lrd.d.list.get(lrd.index);  // "."后面的符号
			if(!result.contains(s))
			{
				result.add(s);
			}
		}
		return result;
	}
	
	/**
	 * 返回一个项目集中，s与项目集"."后面的符号相匹配的，所在的产生式状态列表
	 * @param s 即将读入的符号
	 * @return 产生式状态列表
	 */
	public ArrayList<ProS> getLRDs(String s)
	{
		ArrayList<ProS> result = new ArrayList<ProS>();
		for(ProS lrd:set)
		{
			if(lrd.d.list.size() != lrd.index)  // 非规约状态
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
