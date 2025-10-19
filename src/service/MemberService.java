package service;

import model.Member;
import repository.MemberRepository;
import java.util.List;

public class MemberService {
    private final MemberRepository memberRepo = new MemberRepository();

    public void registerMember(String name, String email, String phone) {
        Member m = memberRepo.addMember(name, email, phone);
        if (m != null) {
            System.out.println("Đăng ký độc giả thành công!");
        }
    }

    public void showAllMembers() {
        List<Member> list = memberRepo.getAllMembers();
        if (list.isEmpty()) {
            System.out.println("Chưa có độc giả nào.");
        } else {
            for (Member m : list) {
                m.displayInfo();
            }
        }
    }

    public void findMemberById(int id) {
        Member m = memberRepo.getMemberById(id);
        if (m == null) {
            System.out.println("Không tìm thấy độc giả có ID = " + id);
        } else {
            m.displayInfo();
        }
    }
}
