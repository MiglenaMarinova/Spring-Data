package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordsRootSeedDto {
    @XmlElement(name = "borrowing_record")
    private List<RecordSeedDto> records;

    public RecordsRootSeedDto() {
    }

    public List<RecordSeedDto> getRecords() {
        return records;
    }

    public void setRecords(List<RecordSeedDto> records) {
        this.records = records;
    }
}
