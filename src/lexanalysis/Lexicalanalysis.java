package lexanalysis;

import java.util.*;


public class Lexicalanalysis
{
	private String text;  // ����Ĳ��������ı�
	
	private ArrayList<Token> tokenList;  // 
	/**
	 * ���캯��
	 * @param text String�ͣ�����Ĳ��������ı�
	 */
	public Lexicalanalysis(String text, ArrayList<Token> tokenList)
	{
		this.text = text;
		this.tokenList = tokenList;
	}

	public static int symbol_pos = 0;  // ��¼���ű�λ��
	public static Map<String, Integer> symbol = new HashMap<String, Integer>();  // ���ű�HashMap
	
	public static int constant_pos = 0;  // ��¼����λ��
	public static Map<String, Integer> constant = new HashMap<String, Integer>();  // ������HashMap
	
	/**
	 * �������ݣ�ʶ����Ӧ��Ϣ
	 */
	public void analyze()
	{
		String[] texts = text.split("\n");
		symbol.clear();
		symbol_pos = 0;
		for(int m = 0; m < texts.length; m++)
		{
			String str = texts[m];
			if (str.equals(""))
				continue;
			else 
			{
				char[] strline = str.toCharArray();
				for(int i = 0; i < strline.length; i++) 
				{
					char ch = strline[i];
					if (ch == ' ')
						continue;	
					
					String token = "";  
					
					if (util.isAlpha(ch)) // ʶ��ؼ��ֺͱ�ʶ��  
                    {  
                        do 
                        {  
                            token += ch;  
                            i++;  
                            if(i >= strline.length) 
                            	break;  
                            ch = strline[i];  
                        } while (ch != '\0' && (util.isAlpha(ch) || util.isDigit(ch)));  
                        i--; 
                        if (util.isKeyword(token))  // ʶ��ؼ���
                        {
                            tokenList.add(new Token(m+1,token,util.keywords_code.get(token)));
                        }                      
                        else  // ʶ���ʶ��
                        {
                        	if (symbol.isEmpty() || ( !symbol.containsKey(token)))
                        	{  
                                symbol.put(token, symbol_pos);
                                symbol_pos++;
                            }
                            tokenList.add(new Token(m+1,token,1));
                        }
                        token = "";
                    }
					
					else if(util.isDigit(ch))  // ʶ���޷�����
					{
						int state = 1;
						int k;
                        Boolean isfloat = false;  
                        Boolean isSci_not = false;  
                        while ( (ch != '\0') && (util.isDigit(ch) || ch == '.' || ch == 'e' 
                        		|| ch == '-' || ch == 'E' || ch == '+'))
                        {
                        	if (ch == '.') 
                        		isfloat = true;
                        	if (ch == 'e' || ch == 'E')  
                        	{
                        		isfloat = false;
                        		isSci_not = true;
                        	}
                        	
                            for (k = 0; k <= 6; k++) 
                            {                             	
                                char tmpstr[] = util.digitDFA[state].toCharArray();  
                                if (ch != '#' && util.in_digitDFA(ch, tmpstr[k]) == 1) 
                                {  
                                    token += ch;  
                                    state = k;  
                                    break;  
                                }  
                            }
                            if (k > 6) 
                            	break;
                            i++;
                            if (i >= strline.length) 
                            	break;  
                            ch = strline[i]; 
                            
                            //System.out.println(state);
                            
                        }
                        
                        Boolean haveMistake = false;  
                        
                        if (state == 2 || state == 4 || state == 5)  // ����̬
                        {  
                            haveMistake = true;  
                        }                     
                        else  // �޷�������������ķ��Ŵ���
                        {  
                            if ((ch == '.') || (!util.isOperator(String.valueOf(ch)) 
                            		&& !util.isDigit(ch) && !util.isDelimiter(String.valueOf(ch))
                            		&& ch != ' ')) 
                                haveMistake = true;  
                        }  
                                            
                        if (haveMistake)   // ������ 
                        {  
                        	while (ch != '\0' && ch != ',' && ch != ';' && ch != ' ')
                            {  
                                token += ch;  
                                i++;
                                if (i >= strline.length) 
                                	break;  
                                ch = strline[i];  
                            }
                        }
                        else 
                        {  
                        	if (constant.isEmpty() || ( !constant.containsKey(token)))
                        	{  
                        		constant.put(token, constant_pos);
                                constant_pos++;
                            }
                        	if (isSci_not)
                        	{
                                tokenList.add(new Token(m+1,token,4));
                            } 
                        	else if (isfloat) 
                            {
                                tokenList.add(new Token(m+1,token,3));
                            } 
                            else
                            {
                                tokenList.add(new Token(m+1,token,2));
                            }  
                        }
                        i--;
                        token = "";
                    }
					
					else if(ch == '\'')  // ʶ���ַ�����
					{
						int state = 0;				        
                        token += ch;                    
                        while (state != 3) 
                        {  
                            i++;
                            if (i >= strline.length) 
                            	break;
                            ch = strline[i]; 
                            Boolean flag = false;
                            for (int k = 0; k < 4; k++) 
                            {  
                                char tmpstr[] = util.charDFA[state].toCharArray();  
                                if (util.in_charDFA(ch, tmpstr[k])) 
                                {            
                                    token += ch;
                                    state = k; 
                                    flag = true;
                                    break;  
                                }  
                            }  
                            if (flag == false)
                            	break;
                        }
                        if (state != 3) 
                        {  
                        	//DefaultTableModel tableModel2 = (DefaultTableModel) jtable2.getModel();
                            //tableModel2.addRow(new Object[] {m+1, token, "�ַ���������δ���"});
                            //jtable2.invalidate();
                            i--;  
                        } 
                        else 
                        {  
                        	if (constant.isEmpty() || (!constant.containsKey(token)))
                        	{  
                        		constant.put(token, constant_pos);   
                                //DefaultTableModel tableModel4 = (DefaultTableModel) jtable4.getModel();
                                //tableModel4.addRow(new Object[] {token, constant_pos});
                                //jtable4.invalidate();
                                constant_pos++;
                            }
                        	//DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                            //tableModel1.addRow(new Object[] {m+1, token, "�ַ�����", 5});
                            tokenList.add(new Token(m+1,token,5));
                            //jtable1.invalidate(); 
                        }
                        token = "";
					}

					else if (ch == '"')  // ʶ���ַ�������
					{
						Boolean haveMistake = false;
						String str1 = "";  
						str1 += ch;  

                        int state = 0;  
                        
						
                        while (state != 3) 
                        {  
                            i++;                             
                            if (i>=strline.length-1) 
                            {  
                                haveMistake = true;  
                                break;  
                            }                              
                            ch = strline[i]; 
                            if (ch == '\0') 
                            {  
                                haveMistake = true;  
                                break;  
                            }
                            for (int k = 0; k < 4; k++) 
                            {  
                                char tmpstr[] = util.stringDFA[state].toCharArray();  
                                if (util.in_stringDFA(ch, tmpstr[k])) 
                                {  
                                	str1 += ch;  
                                    if (k == 2 && state == 1)  // ת���ַ�  
                                    {  
                                        if (util.isEsSt(ch))
                                            token = token + '\\' + ch;  
                                        else  
                                            token += ch;  
                                    } 
                                    else if (k != 3 && k != 1)  
                                        token += ch;  
                                    state = k;  
                                    break;  
                                }  
                            }  
                        }
                        if (haveMistake) 
                        {   
                        	//DefaultTableModel tableModel2 = (DefaultTableModel) jtable2.getModel();
                            //tableModel2.addRow(new Object[] {m+1, str1, "�ַ�����������δ���"});
                            //jtable2.invalidate();  
                            i--;  
                        } 
                        else 
                        {  
                        	if (constant.isEmpty() || (!constant.containsKey(token)))
                        	{  
                        		constant.put(token, constant_pos);   
                                //DefaultTableModel tableModel4 = (DefaultTableModel) jtable4.getModel();
                                //tableModel4.addRow(new Object[] {str1, constant_pos});
                                //jtable4.invalidate();
                                constant_pos++;
                            }
                        	//DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                            //tableModel1.addRow(new Object[] {m+1, str1, "�ַ�������", 6});
                            tokenList.add(new Token(m+1,str1,6));
                            //jtable1.invalidate();  
                        }  
                        token = "";		
					}
					
					
					else if (ch == '/')  //  ʶ��ע��//
					{
						token += ch;  
                        i++;
                        if (i>=strline.length) 
                        	break;  
                        ch = strline[i];
                        
						//���Ƕ���ע�ͼ�����ע��
                        if (ch != '*' && ch != '/')   
                        {  
                            if (ch == '=')  
                                token += ch; // /=  
                            else 
                            {  
                                i--; // ָ����� // /  
                            }  
                            //DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                            //tableModel1.addRow(new Object[] {m+1, token, "�����", util.operator_code.get(token)});
                            tokenList.add(new Token(m+1,token,util.operator_code.get(token)));
                            //jtable1.invalidate();    
                            token = "";  
                        }
                        // ע�Ϳ����ǡ�//��Ҳ�����ǡ�/*��
                        else 
                        {
                        	Boolean haveMistake = false;
                        	int State = 0;
                        	if (ch == '*') 
                        	{  
                        		// ch == '*'
                        		token += ch;  
                                int state = 2;  

                                while (state != 4) 
                                {                                      
                                    if (i == strline.length-1) 
                                    {  
                                    	token += '\n';  
                                    	m++;
                                    	if (m >= texts.length)
                                    	{
                                    		haveMistake = true;  
                                            break;  
                                    	}
                                		str = texts[m];
                                		if (str.equals(""))
                                			continue;
                                		else 
                                		{
                                			strline = str.toCharArray();
                                			i=0;
                                			ch = strline[i];
                                		}
                                    }  
                                    else
                                    {
                                    	i++;
	                                    ch = strline[i];
                                    }
                               
                                    for (int k = 2; k <= 4; k++) 
                                    {  
                                        char tmpstr[] = util.noteDFA[state].toCharArray();  
                                        if (util.in_noteDFA(ch, tmpstr[k], state)) 
                                        {  
                                            token += ch;  
                                            state = k;  
                                            break;  
                                        }  
                                    }  
                                }
                                State = state;
                            }
                        	else if(ch == '/')
                        	{
                        		//����ע�Ͷ�ȡ�����ַ�
                        		int index = str.lastIndexOf("//");  
                                
                                String tmpstr = str.substring(index);  
                                int tmpint = tmpstr.length();  
                                for(int k=0;k<tmpint;k++)                                     
                                  i++;    
                                token = tmpstr;
                                State = 4;
                        	}
                        	if(haveMistake || State != 4)
                        	{
                        		//DefaultTableModel tableModel2 = (DefaultTableModel) jtable2.getModel();
                                //tableModel2.addRow(new Object[] {m+1, token, "ע��δ���"});
                                //jtable2.invalidate();  
                                --i;
                        	}
                        	else
                        	{
                        		//DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                                //tableModel1.addRow(new Object[] {m+1, token, "ע��", 7});
                                tokenList.add(new Token(m+1,token,7));
                                //jtable1.invalidate();
                        	}
                        	token = "";
                        }	
					}
					
					else if (util.isOperator(String.valueOf(ch)) || util.isDelimiter(String.valueOf(ch)))  // ������ͽ��
                    {  
						token += ch; 						
                        if (util.isPlusEqu(ch))  // ���������һ��"="
                        {  
                            i++;
                            if (i>=strline.length) 
                            	break;  
                            ch = strline[i];  
                            if (ch == '=')  
                                token += ch;  
                            else 
                            {                              	
                            	i--;   
                            }  
                        }                  
                        if(token.length() == 1)  //�ж��Ƿ�Ϊ���
                        {
                        	String signal = token;
                        	if(util.isDelimiter(signal))
                        	{
                        		//DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                                //tableModel1.addRow(new Object[] {m+1, token, "���", util.delimiter_code.get(token)});
                                tokenList.add(new Token(m+1,token,util.delimiter_code.get(token)));
                                //jtable1.invalidate();
                        	}                        
                        	else
                        	{
                        		//DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                                //tableModel1.addRow(new Object[] {m+1, token, "�����", util.operator_code.get(token)});
                                tokenList.add(new Token(m+1,token,util.operator_code.get(token)));
                                //jtable1.invalidate();
                        	}
                        }
                        else
                        {
                        	//DefaultTableModel tableModel1 = (DefaultTableModel) jtable1.getModel();
                            //tableModel1.addRow(new Object[] {m+1, token, "�����", util.operator_code.get(token)});
                            tokenList.add(new Token(m+1,token,util.operator_code.get(token)));
                            //jtable1.invalidate();
                        }                        
                        token = "";		
                    }
					
					else  //���Ϸ��ַ�
                    {  
                        if(ch != ' ' && ch != '\t' && ch != '\0' && ch != '\n' && ch != '\r')  
                        {
                            System.out.println(ch);
                        }  
                    }				
				}
			} 
		}
    }
}