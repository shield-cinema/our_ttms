/**
 *
 */
package idao;

import domain.Studio;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface IStudioDAO //接口，所有定义，在dao里实现
{
	public int insert(Studio stu);
	public int update(Studio stu);
	public int delete(int ID);
	public List<Studio> select(String condt);
}
