package calculator;
/**
 *	����� �����������
 *	@author  Kostikov Aleksey
 *	@since   5.07.2016
 *	@version 1
 */
public class Calculator{
	
	/** ��������� ��������*/
	private double result;
	
	/** 
	 *	�������� 
	 *	@param first  - ������ ��������
	 *	@param second - ������ ��������
	 */
	public void add(double first, double second){
		this.result = first+second;
	}
	/** 
	 *	��������� 
	 *	@param first  - ������ ��������
	 *	@param second - ������ ��������
	 */
	public void subtract(double first, double second){
		this.result = first-second;
	}
	/** 
	 *	������� 
	 *	@param first  - ������ ��������
	 *	@param second - ������ ��������
	 */
	public void div(double first, double second){
		this.result = first/second;
	}
	
	/** 
	 *	��������� 
	 *	@param first  - ������ ��������
	 *	@param second - ������ ��������
	 */
	public void mult(double first, double second){
		this.result = first*second;
	}
	
	/** 
	 *	����� ����������
	 *	@return ��������� ������� 
	*/
	public double getResult()
	{
		return this.result;
	}
}