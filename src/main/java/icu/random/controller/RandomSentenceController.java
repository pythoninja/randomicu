package icu.random.controller;

import static icu.random.api.ControllerPaths.API_ROOT_PATH;
import static icu.random.api.ControllerPaths.RANDOM_SENTENCES_GENERATOR_PATH;
import static icu.random.api.ControllerPaths.RANDOM_SENTENCE_GENERATOR_PATH;
import icu.random.model.Sentence;
import icu.random.service.RandomSentenceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_ROOT_PATH)
public class RandomSentenceController {

  private final RandomSentenceService randomSentenceService;

  @Autowired
  public RandomSentenceController(RandomSentenceService randomSentenceService) {
    this.randomSentenceService = randomSentenceService;
  }

  @GetMapping(value = RANDOM_SENTENCE_GENERATOR_PATH)
  public Sentence randomSentenceJsonGenerator(@RequestParam(required = false) Integer count,
                                              @RequestParam(defaultValue = "true") boolean enableParagraphs) {

    return randomSentenceService.getRandomSentence(count, enableParagraphs);
  }

  @GetMapping(value = RANDOM_SENTENCE_GENERATOR_PATH,
      produces = {MediaType.TEXT_PLAIN_VALUE})
  public String randomSentenceTextGenerator(@RequestParam(required = false) Integer count,
                                            @RequestParam(defaultValue = "true") boolean enableParagraphs) {

    return randomSentenceService.getRandomSentence(count, enableParagraphs).getSentence();
  }

  @GetMapping(value = RANDOM_SENTENCES_GENERATOR_PATH)
  public Map<String, List<Sentence>> randomSentencesJsonGenerator(@RequestParam(required = false) Integer count,
                                                                  @RequestParam(required = false) Integer sentencesCount,
                                                                  @RequestParam(defaultValue = "true") boolean enableParagraphs) {

    return randomSentenceService.getRandomSentences(count, sentencesCount, enableParagraphs);
  }
}
