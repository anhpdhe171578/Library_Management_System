package repository;

import model.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    private int nextId = 1;

    public boolean add(Member member) {
        Member newMember = new Member(nextId++, member.getName(), member.getEmail(), member.getPhone());
        members.add(newMember);
        return true;
    }

    public List<Member> getAll() {
        return members;
    }

    public Member findById(int id) {
        for (Member m : members) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public Member findByEmail(String email) {
        for (Member m : members) {
            if (m.getEmail().equalsIgnoreCase(email)) return m;
        }
        return null;
    }
}
