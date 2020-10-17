package service;


import com.tgdz.fkptceshi.entity.AIAnalysis;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface IVideoAnalyzeService {


	void saveRlsbData(String sb)throws Exception;

    void helmetAi(AIAnalysis aiAnalysis) throws Exception;
}
