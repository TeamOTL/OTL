//
//public class BoardRepositoryTests
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import com.otl.otl.domain.Board;
//
//@SpringBootTest
//@Log4j2
//public class BoardRepositoryTests {
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    public void testSearch1() {
//
//        //2 page order by bno desc
//        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
//
//        boardRepository.search1(pageable);
//
//    }
//
//    @Test
//    public void testSearchAll() {
//
//        String[] types = {"t","c"}; // ,"w"는 닉네임으로 받을 예정
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable );
//
//    }
//
//    @Test
//    public void testSearchAll2() {
//
//        String[] types = {"t","c"}; // ,"w"는 닉네임으로 받을 예정
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable );
//
//        //total pages
//        log.info(result.getTotalPages());
//
//        //pag size
//        log.info(result.getSize());
//
//        //pageNumber
//        log.info(result.getNumber());
//
//        //prev next
//        log.info(result.hasPrevious() +": " + result.hasNext());
//
//        result.getContent().forEach(board -> log.info(board));
//    }
//}
//
