package repository;

import model.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private final List<Member> members = new ArrayList<>();
    private int nextId = 1;


    public Member addMember(String name, String email, String phone) {

        for (Member m : members) {
            if (m.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email đã tồn tại! Không thể đăng ký trùng.");
                return null;
            }
        }

        Member newMember = new Member(nextId++, name, email, phone);
        members.add(newMember);
        return newMember;
    }


    public List<Member> getAllMembers() {
        return members;
    }


    public Member getMemberById(int id) {
        for (Member m : members) {
            if (m.getId() == id) return m;
        }
        return null;
    }
}
