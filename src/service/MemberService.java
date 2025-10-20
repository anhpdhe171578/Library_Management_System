package service;

import model.Member;
import repository.MemberRepository;
import java.util.List;

public class MemberService {
    private MemberRepository memberRepo = new MemberRepository();

    public MemberRepository getRepo() {
        return memberRepo;
    }

    public boolean add(Member member) {
        if (memberRepo.findByEmail(member.getEmail()) != null) {
            return false;
        }
        return memberRepo.add(member);
    }

    public List<Member> getAll() {
        return memberRepo.getAll();
    }

    public Member findById(int id) {
        return memberRepo.findById(id);
    }

    public Member findByEmail(String email) {
        for (Member m : memberRepo.getAll()) {
            if (m.getEmail().equalsIgnoreCase(email)) {
                return m;
            }
        }
        return null;
    }
}
