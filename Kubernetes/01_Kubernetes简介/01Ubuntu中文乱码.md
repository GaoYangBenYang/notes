##ubuntu����֧�֣���������������

 - ״�������õ�Linuxϵͳ��֧�����ģ��������ľ�zz�ˡ�

 - Ŀ�꣺ʹϵͳ/������֧�����ģ��ܹ�������ʾ��

####���ȣ���װ����֧�ְ�language-pack-zh-hans��

    sudo apt-get install language-pack-zh-hans

####Ȼ���޸�/etc/environment�����ļ���ĩβ׷�ӣ���

    LANG="zh_CN.UTF-8"
    LANGUAGE="zh_CN:zh:en_US:en"

####���޸�/var/lib/locales/supported.d/local(û������ļ����½���ͬ����ĩβ׷��)��

    en_US.UTF-8 UTF-8
    zh_CN.UTF-8 UTF-8
    zh_CN.GBK GBK
    zh_CN GB2312

####���ִ�����

    sudo locale-gen

####�������������ǿո���������װ������������

    sudo apt-get install fonts-droid-fallback ttf-wqy-zenhei ttf-wqy-microhei fonts-arphic-ukai fonts-arphic-uming

    ���ϣ���������������ʾ������
