/**
 * 
 */
package com.folk.winner.dc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Repository;

import com.folk.winner.dc.domain.Coach;
import com.folk.winner.dc.domain.Jockey;
import com.folk.winner.dc.domain.Race;
import com.folk.winner.dc.domain.RacingHorse;

/**
 * @author fangbe
 *
 */
@Repository
public class RaceCrawlerRepositoryImpl extends AbstractCrawlerRepository implements RaceCrawlerRepository {
	
	
	/*
	 * (non-Javadoc)
	 * @see com.folk.winner.dc.repository.CrawlerRepository#read(java.lang.String)
	 */
	@Override
	public Race read(String url) {
		load(url);
		authenticate();
		
		Race race = new Race();
		setNameAndOrder(race);
		setLengthAndRopePosition(race);
		setRacingHorses(race);
		
		return race;
		
	}

	/**
	 * 
	 * @param race
	 */
	private void setRacingHorses(Race race) {
		List<WebElement> tables = webDriver.findElements(By.xpath("//*[@id='dt_partants']/table"));
		if (tables == null || tables.isEmpty()) {
			return;
		}

		for (WebElement t : tables) {
			List<WebElement> tbodies = t.findElements(By.xpath("tbody"));
			WebElement body = (tbodies == null || tbodies.size() == 0) ? t : (tbodies.size() > 1 ? tbodies.get(1) : tbodies.get(0));
			List<WebElement> rows = body.findElements(By.xpath("tr"));
			List<RacingHorse> horses = new ArrayList<>(rows.size());
			for (WebElement row : rows) {
				RacingHorse horse = buildRacingHorce(race, row);
				horses.add(horse);
			}
			race.setRacingHorses(horses);
		}
	}

	/**
	 * 
	 * @param race
	 * @param row
	 * @return
	 */
	private RacingHorse buildRacingHorce(Race race, WebElement row) {
		RacingHorse horse = new RacingHorse();
		horse.setRace(race);
		
		List<WebElement> cells = row.findElements(By.xpath("td"));
		
		WebElement nameCell = cells.get(1);
		horse.setName(nameCell.getText().trim());
		
		WebElement anchror = nameCell.findElement(By.xpath("div/span/a"));
		horse.setUrl(anchror.getAttribute("href"));
		
		WebElement ropeCell = cells.get(2);
		horse.setRope(Integer.valueOf(ropeCell.getText()));
		
		WebElement weightCell = cells.get(4);
		horse.setWeight(Float.valueOf(weightCell.getText()));
		
		WebElement img = nameCell.findElement(By.xpath("div/span/img"));
		String alt = img != null ? img.getAttribute("alt").trim() : null;
		if("logo oeilleres australiennes".equalsIgnoreCase(alt)) {
			horse.setAccessories("OA");
		} else if("logo oeilleres".equalsIgnoreCase(alt)) {
			horse.setAccessories("O");
		}
		
		WebElement valueCell = cells.get(9);
		String value = valueCell.getText().trim();
		horse.setValue(value != null && !"".equals(value) ? Float.valueOf(ropeCell.getText()) : 0);
		
		WebElement jockeyCell = cells.get(6);
		Jockey jockey = new Jockey();
		jockey.setName(jockeyCell.getText().trim());
		
		WebElement jockeyAnchror = jockeyCell.findElement(By.xpath("div/a"));
		jockey.setUrl(jockeyAnchror.getAttribute("href"));
		horse.setJockey(jockey);
		
		WebElement coachCell = cells.get(7);
		Coach coach = new Coach();
		coach.setName(coachCell.getText().trim());
		
		WebElement coachAnchror = coachCell.findElement(By.xpath("div/a"));
		coach.setUrl(coachAnchror.getAttribute("href"));
		horse.setCoach(coach);
		
		return horse;
	}

	/**
	 * @param race
	 */
	protected void setLengthAndRopePosition(Race race) {
		Optional<List<String>> raceInfo = getContentValues("//*[@id='yui-main']/div/div[2]/span[1]");
		raceInfo.ifPresent(c -> {
			String content = c.get(0);
			
			int pos1 = content.indexOf("€ - ");
			int pos2 = content.indexOf("m - ", pos1 + 4);
			String length = content.substring(pos1 + 4, pos2);
			race.setLength(Float.valueOf(length));
			
			int p1 = content.indexOf(" - corde : à ");
			String position = content.substring(p1 + 13, p1 + 13 + 6);
			race.setRopePosition("droite".equalsIgnoreCase(position) ? "D" : ("gauche".equalsIgnoreCase(position) ? "G" : null));
		});
	}

	/**
	 * @param race
	 */
	private void setNameAndOrder(Race race) {
		Optional<List<String>> namesOptional = getContentValues("//*[@id='yui-main']/div/div[2]/div[3]/span/strong");
		namesOptional.ifPresent(c -> {
			String content = c.get(0);
			String[] values = content.split("-");
			race.setName(values[1].trim());
			
			String order = values[0].trim().replace("ère course", "").replace("ème course", "");
			race.setOrder(Integer.valueOf(order));
		});
	}

}
