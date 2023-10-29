package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.dto.CarDTO;

class CarsTest {
    Cars cars;
    Car car1;
    Car car2;

    @BeforeEach
    void beforeEach(){
        car1 = new Car("hoho");
        car2 = new Car("koko");
        cars = new Cars(Arrays.asList(car1, car2));
    }

    @Test
    void 같은_위치인_경우_승자(){
        // then
        List<CarDTO> expected = Arrays.asList(CarDTO.from(car1), CarDTO.from(car2));
        Assertions.assertThat(cars.getWinners())
                .usingElementComparator((a, b) -> {
                    return a.getName().compareTo(b.getName());
                }).isEqualTo(expected);
    }

    @Test
    void 다른_위치인_경우_승자(){
        // given
        IntStream.range(0, 5).forEach(i -> {
            car1.move(4);
        });
        // then
        List<CarDTO> expected = Arrays.asList(CarDTO.from(car1));
        Assertions.assertThat(cars.getWinners())
                .usingElementComparator((a, b) -> {
                    return a.getName().compareTo(b.getName());
                }).isEqualTo(expected);
    }

}