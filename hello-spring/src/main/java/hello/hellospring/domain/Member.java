package hello.hellospring.domain;

public class Member { //id, name 두가지의 요구 사항 (멤버)
    private Long id;  //이메일 값 (시스템이 저장하는 아이디 , 데이터를 구분하기 위해서)
    private String name;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
