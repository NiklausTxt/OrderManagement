package com.orderManagement.command.impl;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.util.Console;

public class HelpCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("��ӭ������ϵͳ����ѡ����������");
		Console.println("Login--�û���¼");
		Console.println("Signup--�û�ע��");
		Console.println("exit--�˳�");
		Console.println("Help--��ʾ������Ϣ");
		Console.println("==========��¼֮��============");
		Console.println("order--�¶���");
		Console.println("query--��ѯ���ж���");
		Console.println("pay--֧������");
		Console.println("recharge--�˻���ֵ");
		Console.println("account--��ʾ�˻����");
	}

}
