/**
 * 
 */
package idao;
import domain.*;

import java.util.List;

import domain.Studio;

/**
 * @author Administrator
 *
 */
public interface IStudioDAO //�ӿڣ����ж��壬��dao��ʵ��
{
	public int insert(Studio stu);
	public int update(Studio stu);
	public int delete(int ID);
	public List<Studio> select(String condt); 
}
