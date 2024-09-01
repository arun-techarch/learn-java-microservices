package com.aruntech.service;

import java.util.Objects;
import com.aruntech.entity.Banking_Sequence;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class BankingSeqGenerator {
    @Autowired
    private MongoOperations mongoOperations;

    public long getSequence(final String seqName) {
        Query query = new Query(Criteria.where("id").is(seqName));
        Update update = new Update().inc("seq", 1);

        Banking_Sequence counter = mongoOperations.findAndModify(
                query, update, options().returnNew(true).upsert(true),
                Banking_Sequence.class
        );
        return !Objects.isNull(counter)? counter.getSeq() : 1;
    }
}
