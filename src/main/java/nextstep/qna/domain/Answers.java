package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAll(NsUser loginUser) throws CannotDeleteException {
        if (CollectionUtils.isEmpty(answers)) {
            return;
        }

        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public void addDeleteHistories(List<DeleteHistory> deleteHistories) {
        answers.stream()
                .map(Answer::createDeleteHistory)
                .forEach(deleteHistories::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }


}
